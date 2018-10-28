package com.datastatistics.ford.controller;

import static org.hamcrest.CoreMatchers.is;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import java.util.ArrayList;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import com.datastatistics.ford.service.FordDataService;


/**
 * JUnit Test class for FordDataController
 * 
 * @author Monika Tantia
 *
 */
@RunWith(SpringRunner.class)
@WebMvcTest(value=FordDataController.class)
public class FordDataControllerTest {

//	private final Logger LOGGER = LogManager.getLogger(this.getClass());

	  @Autowired
	    private MockMvc mvc;

	    @MockBean
	    private FordDataService fordDataService;


	    @Test
	    public void getStockCloseRateOverTime() throws Exception {
	    	com.datastatistics.ford.domain.FordStockData stockCloseRateModel 
	    							= new com.datastatistics.ford.domain.FordStockData();

	    	java.util.List<com.datastatistics.ford.domain.FordStockData> listStockData = 
	    			new ArrayList<com.datastatistics.ford.domain.FordStockData>();
	    	listStockData.add(stockCloseRateModel);
	        when(fordDataService.getCloseRatesOverTime())
	        	.thenReturn(listStockData);

	        mvc.perform(get("/fordDataStatistics/closeRates")
	                .contentType(MediaType.APPLICATION_JSON))
	                .andExpect(status().isOk());
	    }

	    @Test
	    public void getAverageCloseOverAPeriod() throws Exception {

	    	com.datastatistics.ford.domain.FordStockData fordStockData 
	    				= new com.datastatistics.ford.domain.FordStockData();
	        fordStockData.setYear(1996);
	        
	        java.util.List<com.datastatistics.ford.domain.FordStockData> listStockData = 
	    			new ArrayList<com.datastatistics.ford.domain.FordStockData>();
	    	listStockData.add(fordStockData);
	        
	        when(fordDataService.getAvgCloseOverPeriod(Mockito.anyInt(),
	        										Mockito.anyInt(),
	        										Mockito.anyInt()))
	        										.thenReturn(listStockData);

	        mvc.perform(get("/fordDataStatistics/avgClose?year=1996")
	                .contentType(MediaType.APPLICATION_JSON))
	                .andExpect(status().isOk());
	    }
	}
