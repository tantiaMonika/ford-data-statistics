package com.datastatistics.ford.service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.YearMonth;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.datastatistics.ford.exception.FordDataNotFoundException;
import com.datastatistics.ford.commons.ApplicationConstants;
import com.datastatistics.ford.entity.FordStockData;
import com.datastatistics.ford.repository.FordDataRepository;

/**
 * Service class for processing the data for incoming request
 * 
 * @author Monika Tantia
 *
 */
@Service
public class FordDataService {

	@Autowired
	private FordDataRepository fordDataRepository;

	private final Logger LOGGER = LogManager.getLogger(this.getClass());

	/**
	 * calculates close rate for each date and return the day wise close rates in a
	 * map form.
	 * 
	 * @return Map
	 */
	public List<com.datastatistics.ford.domain.FordStockData> getCloseRatesOverTime() {
		LOGGER.info("start of getCloseRatesOverTime");

		List<FordStockData> fordDataBeanList = new ArrayList<>();
		
			fordDataRepository.findOpenCloseDateByOrderByDateAsc().forEach(fordDataBeanList::add);

			if (fordDataBeanList.isEmpty()) {
				throw new FordDataNotFoundException(ApplicationConstants.DATA_NOT_FOUND);
			}
			
			List<com.datastatistics.ford.domain.FordStockData> domainList = new ArrayList<>();
			for (FordStockData tempBean : fordDataBeanList) {
				BigDecimal dailyCloseRate
							= ((tempBean.getClose()
								.subtract(tempBean.getOpen()))
								.multiply(new BigDecimal(100)))
								.divide(tempBean.getClose(), 6, RoundingMode.HALF_UP);
				
				com.datastatistics.ford.domain.FordStockData domainFord = new com.datastatistics.ford.domain
						.FordStockData();
				domainFord.setDate(tempBean.getDate());
				domainFord.setClose(dailyCloseRate);
				domainList.add(domainFord);
			}
			LOGGER.info("end of getCloseRatesOverTime");
					
		return domainList;
	}

	/**
	 * Calculates the average close data over given combination of year, month and
	 * day and return the same in map form
	 * 
	 * @param year
	 * @param month
	 * @param day
	 * @return
	 */
	// yet to complete the logic
	public List<com.datastatistics.ford.domain.FordStockData> getAvgCloseOverPeriod(Integer year, Integer month, Integer day) {
		LOGGER.info("start of getAvgCloseOverPeriod.");
		List<com.datastatistics.ford.domain.FordStockData> domainList = new ArrayList<>();

		if (null != day && null != month && null != year) {
			this.getAvgOverDay(domainList, year, month, day);

		} else if (null == day && null != month && null != year) {
			this.getAvgOverMonth(domainList, year, month);
		}

		else if (null == day && null == month && null != year) {
			this.getAvgOverYear(domainList, year);
		}
		if (domainList.isEmpty()) {
			throw new FordDataNotFoundException(ApplicationConstants.DATA_NOT_FOUND);
		}

		LOGGER.info("end of getAvgCloseOverPeriod");
		return domainList;

	}

	/**
	 * Calculates the average close data over given year and return the same in map
	 * form
	 * 
	 * @param avgCloseOverPeriod
	 * @param year
	 */
	private void getAvgOverYear(List<com.datastatistics.ford.domain.FordStockData> domainList, Integer year) {

		List<FordStockData> fordDataBeanIterator = fordDataRepository.findByYear(year);

		// yet to complete the logic
		HashMap<String, BigDecimal> avgCloseOverPeriod = new HashMap<>();
		for (Object tempObj : fordDataBeanIterator) {
			FordStockData tempBean = (FordStockData) tempObj;

			if (avgCloseOverPeriod.containsKey(tempBean.getMonth() + "/" + year)) {

				avgCloseOverPeriod.put(tempBean.getMonth() + "/" + year,
						avgCloseOverPeriod.get(tempBean.getMonth() + "/" + year).add(tempBean.getClose()));
			} else {
				avgCloseOverPeriod.put(tempBean.getMonth() + "/" + year, tempBean.getClose());
			}

		}

		if (!avgCloseOverPeriod.isEmpty()) {

			for(Entry<String, BigDecimal> tempEntry : avgCloseOverPeriod.entrySet()) {

				String[] tempArray = tempEntry.getKey().split("/");
				Integer month = Integer.parseInt(tempArray[0]);

				YearMonth yearMonthObject = YearMonth.of(year, month);
				int daysInMonth = yearMonthObject.lengthOfMonth();
				
				com.datastatistics.ford.domain.FordStockData domainFord = new com.datastatistics.ford.domain
						.FordStockData();
				domainFord.setMonth(month);
				domainFord.setYear(year);
				domainFord.setClose(tempEntry.getValue()
									.divide(new BigDecimal(daysInMonth), 6, RoundingMode.HALF_UP));
				domainList.add(domainFord);
				
			}

		}

	}

	/**
	 * Calculates the average close data over given combination of year and month
	 * and return the same in map form
	 * 
	 * @param avgCloseOverPeriod
	 * @param year
	 * @param month
	 */
	private void getAvgOverMonth(List<com.datastatistics.ford.domain.FordStockData> domainList, Integer year, Integer month) {

		Iterable<FordStockData> fordDataBeanIterator = fordDataRepository.findByYearAndMonth(year, month);
		BigDecimal totalCloseOverMonth = BigDecimal.ZERO;
		for (FordStockData tempBean : fordDataBeanIterator) {

		

			totalCloseOverMonth = totalCloseOverMonth.add(tempBean.getClose());
			

		}
		com.datastatistics.ford.domain.FordStockData domainFord = new com.datastatistics.ford.domain
				.FordStockData();
		YearMonth yearMonthObject = YearMonth.of(year, month);
		int daysInMonth = yearMonthObject.lengthOfMonth();
		
		domainFord.setMonth(month);
		domainFord.setYear(year);
		domainFord.setClose(totalCloseOverMonth
							.divide(new BigDecimal(daysInMonth), 6, RoundingMode.HALF_UP));
		domainList.add(domainFord);
	}

	/**
	 * Calculates the average close data over given combination of year, month and
	 * day and return the same in map form
	 * 
	 * @param avgCloseOverPeriod
	 * @param year
	 * @param month
	 * @param day
	 */
	private void getAvgOverDay(List<com.datastatistics.ford.domain.FordStockData> domainList, Integer year, Integer month,
			Integer day) {

		Iterable<FordStockData> fordDataBeanIterator = fordDataRepository.findByYearAndMonthAndDay(year, month, day);

		for (FordStockData tempBean : fordDataBeanIterator) {
			com.datastatistics.ford.domain.FordStockData domainFord = new com.datastatistics.ford.domain
					.FordStockData();
			domainFord.setDate(tempBean.getDate());
			domainFord.setClose(tempBean.getClose());
			domainList.add(domainFord);
			

		}
	}

}
