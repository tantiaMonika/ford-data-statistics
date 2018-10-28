package com.datastatistics.ford;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/** 
 * Main class for FordDataStatistics Spring Boot Application
 * @author Monika Tantia
 */
@SpringBootApplication
public class FordDataStatistics {

	private static final Logger LOGGER = LogManager.getLogger(FordDataStatistics.class);
	
	/**
	 * Main method for FordDataStatistics Spring Boot Application
	 * 
	 * @param args
	 */
	public static void main(String[] args) {

		LOGGER.info("Starting FordDataStatistics, inside main.");
		SpringApplication.run(FordDataStatistics.class, args);

	}

}
