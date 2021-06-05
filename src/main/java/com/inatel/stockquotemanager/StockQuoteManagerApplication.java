package com.inatel.stockquotemanager;

import java.net.URISyntaxException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.inatel.stockquotemanager.client.StockManagerClient;

@SpringBootApplication
public class StockQuoteManagerApplication implements CommandLineRunner {
	
	private static final Logger logger = LoggerFactory.getLogger(StockQuoteManagerApplication.class);
	
	@Autowired
	StockManagerClient notificationClient;
	
	public static void main(String[] args) throws URISyntaxException {
		
		SpringApplication.run(StockQuoteManagerApplication.class, args);
		
	}
	
	@Override
	public void run(String... args) throws URISyntaxException {
		if(!notificationClient.register()) {
			logger.error("Application could not be started, notification service is running?");
			System.exit(1);
		}
		logger.info("Registered in stockManager notification service!");
	}
	
	
}
