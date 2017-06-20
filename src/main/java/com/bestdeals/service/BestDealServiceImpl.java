package com.bestdeals.service;

import com.bestdeals.domain.Deal;
import com.bestdeals.repository.BestDealRepository;
import com.bestdeals.utils.InterestTypes;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;
import java.text.MessageFormat;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;

/**
 * Created by arif on 18/06/2017.
 */
@Service
public class BestDealServiceImpl implements BestDealService{

  @Autowired
  RestTemplate restTemplate;

  @Autowired
  BestDealRepository bestDealRepository;

  @Value("${fx-url}")
  String fxUrl;

  /**
   * This method accepts a Deal object, converted from the json string supplied by the client and calculates the interest based on
   * the interest type provided (Simple or Compound)
   * @param deal (Deal object)
   * @return deal
   */
  @Override
  public Deal calculateAndSaveDeal(Deal deal) {

    final BigDecimal amount = calculateDeal(deal);

    BigDecimal interest = amount.subtract(BigDecimal.valueOf(deal.getPrinciple()));
    String fromAndtoCurrencies = deal.getFromCurrency()+deal.getToCurrency();
    double fxRate = convertBaseCurrency(fromAndtoCurrencies);
    deal.setDealInterestAmount(fxRate > 0 ? BigDecimal.valueOf(fxRate).multiply(interest) : BigDecimal.ZERO);
    deal.setDealAmount(fxRate > 0 ? BigDecimal.valueOf(fxRate).multiply(amount) : BigDecimal.ZERO);
    deal.setBaseCurrencyAmount(amount);
    deal.setBaseCurrencyInterestAmount(interest);
    deal.setCreatedOn(LocalDateTime.now());
    deal.setFxRate(fxRate);
    return bestDealRepository.save(deal);
  }

  /**
   * This method finds all the deals available in the database
   * @return List<Deals>
   */
  @Override
  public List<Deal> findDeals() {
    return bestDealRepository.findAll();
  }

  /**
   * This method finds the deals based on the id passed as an argument.
   * @param id
   * @return Deal
   */
  @Override
  public Deal findDealById(int id) {
    return bestDealRepository.findOne(id);
  }

  /**This method converts the base currency to the provided current. The String pairs will contain the concatenated from and to currency.
   * eg. if 'fromCurrency' is 'GBP' and 'toCurrency is 'USD', then the pairs value should be "GBPUSD"
   * The pairs value will then be passed on to execute the fx currency converter API
   * @param pairs (String)
   * @return Double (currency rate to calculate the deal amount and dela interest)
   */
  @Override
  public Double convertBaseCurrency(final String pairs){
    return new JSONArray(restTemplate.getForObject(MessageFormat.format(fxUrl, pairs), String.class)).getJSONObject(0).getDouble("price");
  }

  /**
   * This private method calculates the simple or compund interest deals based on the input
   * @param deal
   * @return BigDecimal
   */
  private BigDecimal calculateDeal(Deal deal){
    Function<Deal, BigDecimal> calculate=null;
    calculate = (d) -> {
      return d.getInterestType().equals(InterestTypes.Compound.name())
          ?
          BigDecimal.valueOf(d.getPrinciple() * Math.pow(1 + (d.getRate() / d.getCompoundingPeriod()), d.getCompoundingPeriod() * d.getTime())).setScale(5, BigDecimal.ROUND_HALF_UP)
          :
          BigDecimal.valueOf(deal.getPrinciple() * deal.getTime() * deal.getRate() / 100).add(BigDecimal.valueOf(deal.getPrinciple())).setScale(5, BigDecimal.ROUND_HALF_UP);

    };
    return  calculate.apply(deal);
  }

}