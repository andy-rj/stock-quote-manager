package com.inatel.stockquotemanager.client;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestOperations;

import com.inatel.stockquotemanager.dto.NotificationDto;
import com.inatel.stockquotemanager.dto.StockConsumingDto;

@Component
public class StockManagerClient {
	@Value("${stockmanager.path}")
	private String stockmanagerPath;
	
	@Value("${stockquotemanager.path}")
	private String stockQuotePath;
	
	@Autowired
	private RestOperations restOperations;
	
	public boolean register() throws URISyntaxException {
		
		HttpHeaders headers = new HttpHeaders();
	    headers.setContentType(MediaType.APPLICATION_JSON);
	    
	    URI uri = new URI(stockmanagerPath+"/notification");
	    
	    NotificationDto notification = new NotificationDto();
	    notification.setHost(stockQuotePath);
	    notification.setPort("8081");
	    
	    
	    ResponseEntity<String> result = restOperations.postForEntity(uri, notification, String.class);
		
		return result.getStatusCodeValue()==200;
		
	}
	
	public boolean exists(final String id) throws URISyntaxException {
		
		HttpHeaders headers = new HttpHeaders();
	    headers.setContentType(MediaType.APPLICATION_JSON);
	    
	    URI uri = new URI(stockmanagerPath+"/stock");
	  
	    ResponseEntity<StockConsumingDto[]> result = restOperations.getForEntity(uri, StockConsumingDto[].class);
		
		return Arrays.asList(result.getBody()).stream().anyMatch(stock -> stock.getId().equals(id));
		
	}
	
	public boolean registerStock(String id, String description) throws URISyntaxException {
		
		HttpHeaders headers = new HttpHeaders();
	    headers.setContentType(MediaType.APPLICATION_JSON);
	    
	    URI uri = new URI(stockmanagerPath+"/stock");
	    
	    StockConsumingDto stock = new StockConsumingDto();
	    stock.setId(id);
	    stock.setDescription(description);
	  
	    ResponseEntity<String> result = restOperations.postForEntity(uri, stock ,String.class);
		
		return result.getStatusCodeValue() == 201;
		
	}

	public StockConsumingDto[] getStocks() throws URISyntaxException {
		HttpHeaders headers = new HttpHeaders();
	    headers.setContentType(MediaType.APPLICATION_JSON);
	    
	    URI uri = new URI(stockmanagerPath+"/stock");
	  
	    ResponseEntity<StockConsumingDto[]> result = restOperations.getForEntity(uri, StockConsumingDto[].class);
		
		return result.getBody();
	}
	


}
