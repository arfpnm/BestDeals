package com.bestdeals.integrated;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Created by arif on 20/06/2017.
 */

@RunWith(SpringRunner.class)
@SpringBootTest
@WebAppConfiguration
public class IntegrationTest {

  private MockMvc mockMvc;
  @Autowired
  private WebApplicationContext context;
  @Autowired
  private RestTemplate restTemplate;
  private String compoundDealJson;
  private String simpleDealJson;
  private final String url = "http://localhost:8080/deals";

  @Before
  public void init() {
    compoundDealJson = "{\n" +
        "\"principle\": 3400,\n" +
        "\"rate\": 4,\n" +
        "\"time\": 3,\n" +
        "\"interestType\": \"Compound\",\n" +
        "\"compoundingPeriod\": 2,\n" +
        "\"fromCurrency\": \"GBP\",\n" +
        "\"toCurrency\": \"USD\"\n" +
        "}";

    simpleDealJson = "{\n" +
        "\"principle\": 3400,\n" +
        "\"rate\": 4,\n" +
        "\"time\": 3,\n" +
        "\"interestType\": \"Simple\",\n" +
        "\"compoundingPeriod\": 2,\n" +
        "\"fromCurrency\": \"GBP\",\n" +
        "\"toCurrency\": \"USD\"\n" +
        "}";

    mockMvc = MockMvcBuilders.webAppContextSetup(context).build();
  }

  /**
   * Make sure that the application is running in the correct port before running the intergated test
   * @throws Exception
   */
  @Test
  @Ignore
  public void testIntegrated() throws Exception {

    mockMvc.perform(post(url).content(compoundDealJson))
        .andExpect(status().isOk());
  }
}
