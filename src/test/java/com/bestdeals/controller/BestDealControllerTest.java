package com.bestdeals.controller;

import com.bestdeals.domain.Deal;
import com.bestdeals.repository.BestDealRepository;
import com.bestdeals.service.BestDealService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;
import java.math.BigDecimal;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest
public class BestDealControllerTest {

	@Mock
	private BestDealService bestDealService;
	@Autowired
	private BestDealController bestDealController;
	@Mock
	private BestDealRepository bestDealRepository;
	private String compoundDealJson;
	private String simpleDealJson;
	private String jsonWithMissingPrincipleValue;
	private Deal deal;

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


		jsonWithMissingPrincipleValue = "{\n" +

				"\"rate\": 4,\n" +
				"\"time\": 3,\n" +
				"\"interestType\": \"Compound\",\n" +
				"\"compoundingPeriod\": 2,\n" +
				"\"fromCurrency\": \"GBP\",\n" +
				"\"toCurrency\": \"USD\"\n" +
				"}";
	}

	@Test
	public void testAddDeals() throws IOException{
		final Deal dealReturn = bestDealController.calculateDealReturns(simpleDealJson);
		assertEquals(3400.0, dealReturn.getPrinciple(), 0);
	}

	@Test
	public void testSimpleInterestCalculation() throws IOException {
		final Deal dealReturn = bestDealController.calculateDealReturns(simpleDealJson);
		assertEquals(dealReturn.getBaseCurrencyInterestAmount(), BigDecimal.valueOf(408.0).setScale(5, BigDecimal.ROUND_HALF_UP));
		assertEquals(dealReturn.getBaseCurrencyAmount(), BigDecimal.valueOf(3808.0).setScale(5, BigDecimal.ROUND_HALF_UP));
		assertEquals("USD", dealReturn.getToCurrency());
	}

	@Test
	public void testCompoundInterestCalculation() throws IOException {
		final Deal dealReturn = bestDealController.calculateDealReturns(compoundDealJson);
		assertEquals(dealReturn.getBaseCurrencyInterestAmount(), BigDecimal.valueOf(2475200.0).setScale(5, BigDecimal.ROUND_HALF_UP));
		assertEquals(dealReturn.getBaseCurrencyAmount(), BigDecimal.valueOf(2478600.0).setScale(5, BigDecimal.ROUND_HALF_UP));
		assertEquals("USD", dealReturn.getToCurrency());
	}

	@Test
	public void testJsonWithMissingValue() throws IOException {
		final Deal dealReturn = bestDealController.calculateDealReturns(jsonWithMissingPrincipleValue);
		assertEquals(dealReturn.getBaseCurrencyInterestAmount(), BigDecimal.valueOf(0.00000).setScale(5, BigDecimal.ROUND_HALF_UP));
		assertEquals(dealReturn.getBaseCurrencyAmount(), BigDecimal.valueOf(0.00000).setScale(5, BigDecimal.ROUND_HALF_UP));
		assertEquals("USD", dealReturn.getToCurrency());
	}

}
