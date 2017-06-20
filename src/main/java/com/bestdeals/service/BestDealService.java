package com.bestdeals.service;

import com.bestdeals.domain.Deal;

import java.math.BigDecimal;
import java.util.List;

/**
 * Created by arif on 18/06/2017.
 */
public interface BestDealService {

  public Deal calculateAndSaveDeal(Deal deal);

  public List<Deal> findDeals();

  public Deal findDealById(int id);

  public Double convertBaseCurrency(String pairs);
}
