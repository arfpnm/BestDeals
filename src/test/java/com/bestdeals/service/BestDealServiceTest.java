package com.bestdeals.service;

import com.bestdeals.domain.Deal;
import com.bestdeals.repository.BestDealRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.when;

/**
 * Created by arif on 18/06/2017.
 */

@RunWith(SpringRunner.class)
@SpringBootTest
public class BestDealServiceTest {

  @Autowired
  BestDealService bestDealService;

  @Mock
  BestDealRepository bestDealRepository;

  private Deal deal;

  @Before
  public void init() {
    initialiseDomain();
  }

  @Test
  public void testCalculateAndSaveDeal(){
    when(bestDealRepository.save(deal)).thenReturn(deal);
    final Deal dealResponse = bestDealService.calculateAndSaveDeal(bestDealService.calculateAndSaveDeal(deal));
    assertEquals(dealResponse.getBaseCurrencyAmount(), this.deal.getBaseCurrencyAmount());
  }

  @Test
  public void testFindDeals(){
    List<Deal> deals = new ArrayList<>();
    deals.add(deal);
    when(bestDealRepository.findAll()).thenReturn(deals);
    final List<Deal> dealsResponse = bestDealService.findDeals();
    assertTrue(dealsResponse.size() > 0);
  }

  @Test
  public void testFindDealById(){
    when(bestDealRepository.findOne(deal.getId())).thenReturn(deal);
    final Deal dealResponse = bestDealService.findDealById(deal.getId());
    assertEquals(BigDecimal.valueOf(3808.00).setScale(2, BigDecimal.ROUND_HALF_UP), dealResponse.getBaseCurrencyAmount());
  }

  private void initialiseDomain(){
    deal = new Deal();
    deal.setId(1);
    deal.setPrinciple(3400);
    deal.setCompoundingPeriod(2);
    deal.setRate(4);
    deal.setTime(3);
    deal.setInterestType("Simple");
    deal.setFromCurrency("GBP");
    deal.setToCurrency("USD");
    deal.setBaseCurrencyAmount(BigDecimal.valueOf(3808));
    deal.setBaseCurrencyInterestAmount(BigDecimal.valueOf(408.0));
  }

}
