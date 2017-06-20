package com.bestdeals.controller;

import com.bestdeals.domain.Deal;
import com.bestdeals.service.BestDealService;
import com.fasterxml.jackson.databind.ObjectMapper;
import jdk.nashorn.internal.parser.JSONParser;
import org.json.HTTP;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;
import java.util.function.Function;

/**
 * Created by arif on 18/06/2017.
 */
@RestController
public class BestDealController {

  @Autowired
  private BestDealService bestDealService;

  /**
   * This controller method accept a json request, and converts to java object ('Deal') and calculates the deals for compound and simple interest and populates required
   * information in deal domain and persist in database (h2 in-memory database used).
   * @param dealJson
   * @return Deal
   * @throws IOException
   */
  @RequestMapping(value = "/deals", method = RequestMethod.POST)
  public Deal calculateDealReturns(@RequestBody String dealJson) throws IOException {
    Deal deal = new ObjectMapper().readValue(dealJson, Deal.class);
    return bestDealService.calculateAndSaveDeal(deal);
  }

  /**
   * This controller method accepts '/id' endpoint and find the data based on the deal id, passed as an argument.
   * @param id
   * @return Deal
   */
  @RequestMapping("deals/id")
  public Deal getDealsById(@PathVariable("id") int id) {
    return bestDealService.findDealById(id);
  }

  /**
   * Gets all the deals available in the DB
   * @return List of Deal objects
   */
  @RequestMapping("deals")
  public List<Deal> getDeals() {
    return bestDealService.findDeals();
  }

}
