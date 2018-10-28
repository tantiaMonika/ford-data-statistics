package com.datastatistics.ford.service;

import com.datastatistics.ford.entity.FordStockData;
import com.datastatistics.ford.repository.FordDataRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Date;
import static org.mockito.Mockito.when;

/**
 * JUnit Test class for FordDataService
 * @author Monika Tantia
 *
 */
@RunWith(MockitoJUnitRunner.Silent.class)
public class FordDataServiceTest {

	private final Logger LOGGER = LogManager.getLogger(this.getClass());

	@Mock
	private FordDataRepository fordDataRepository;

	@InjectMocks
	private FordDataService fordDataService;

	@Test
	public void testGetCloseRatesOverTime() {
		LOGGER.info("start of testGetCloseRatesOverTime");
		FordStockData fordStockData = new FordStockData();
		fordStockData.setClose(new BigDecimal(50));
		fordStockData.setDate(new Date());
		fordStockData.setOpen(new BigDecimal(1));

		when(fordDataRepository.findOpenCloseDateByOrderByDateAsc()).thenReturn(Arrays.asList(fordStockData));
		fordDataService.getCloseRatesOverTime();
		LOGGER.info("end of testGetCloseRatesOverTime");
	}

	@Test
	public void testGetAvgCloseOverPeriod() {
		LOGGER.info("start of testGetAvgCloseOverPeriod");
		FordStockData fordStockData = new FordStockData();
		fordStockData.setDay(null);
		fordStockData.setMonth(12);
		fordStockData.setYear(1996);
		fordStockData.setClose(new BigDecimal(100));
		when(fordDataRepository.findByYearAndMonth(Mockito.anyInt(), Mockito.anyInt())).thenReturn(Arrays.asList(fordStockData));
		fordDataService.getAvgCloseOverPeriod(1996,12,null);
		LOGGER.info("end of testGetAvgCloseOverPeriod");

	}



}