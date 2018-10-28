package com.datastatistics.ford.controller;


import java.util.List;
import java.util.stream.Collectors;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import com.datastatistics.ford.domain.api.FordStockData;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.datastatistics.ford.commons.ApplicationConstants;
import com.datastatistics.ford.service.FordDataService;
/**
 * This class serves as Rest Constroller for the application and provides two APIs specified below:-
 * 1. To get close rate over time for daily stock data
 * 2. To get avg close data over a period for daily stock data
 * @author Monika Tantia 
 */
@RestController
@Validated
@RequestMapping("/fordDataStatistics")
public class FordDataController {

	private final Logger LOGGER = LogManager.getLogger(this.getClass());

	@Autowired
	private FordDataService fordDataService;

	/**
	 * This method handles GET request to get close rate over time for daily stock data.
	 * @return Map of close date values with Date as key
	 */
	@GetMapping("/closeRates")
	public List<FordStockData> getCloseRatesOverTime() {
		LOGGER.info("inside getCloseRatesOverTime");
		return fordDataService.getCloseRatesOverTime()
				.stream()
				.map(com.datastatistics.ford.domain
				.FordStockData::toApi)
				.collect(Collectors.toList());
	}

	/**
	 * This method handles GET request to get avg close data over a period for daily stock data, 
	 * input parameters for this request are mentioned below:-
	 * @param year (Mandatory)
	 * @param month (Optional)
	 * @param day  (Optional)
	 * @return Map of average close data values with requested period as key
	 */
	@GetMapping(value = "/avgClose")
	public List<FordStockData> getAvgCloseOverPeriod(
			@RequestParam @Size(min = 4, max = 4, message = ApplicationConstants.INVALID_YEAR) @Pattern(regexp =
					ApplicationConstants.YEAR_REGEX) String year,
			@RequestParam(required = false) @Min(1) @Max(12) Integer month,
			@RequestParam(required = false) @Min(1) @Max(31) Integer day) {
		LOGGER.info("inside getAvgCloseOverPeriod");
		return fordDataService.getAvgCloseOverPeriod(Integer.parseInt(year), month, day)
				.stream()
				.map(com.datastatistics.ford.domain
				.FordStockData::toApi)
				.collect(Collectors.toList());
	}

}
