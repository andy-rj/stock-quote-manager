package com.inatel.stockquotemanager.controller;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.inatel.stockquotemanager.cache.StockCache;
import com.inatel.stockquotemanager.client.StockManagerClient;
import com.inatel.stockquotemanager.dto.StockDto;
import com.inatel.stockquotemanager.jpa.entity.Stock;
import com.inatel.stockquotemanager.jpa.repository.StockRepository;

@RestController
@RequestMapping("/stock")
public class QuotesController {
	
	private static final Logger logger = LoggerFactory.getLogger(QuotesController.class);
	
	@Autowired
	private StockCache cache;

	@Autowired
	private StockRepository stockRepository;
	
	@Autowired
	private StockManagerClient stockManagerClient;
	
	@GetMapping
	public List<StockDto> list(){
		logger.info("finding all stocks...");
		List<Stock> stocks = stockRepository.findAll();
		return StockDto.converter(stocks);
	}
	
	@GetMapping("/{id}")
	public StockDto getById(@PathVariable String id){
		logger.info(String.format("finding stock [%s]...",id));
		Stock stock = stockRepository.getById(id);
		return new StockDto(stock);
	}
	
	@PostMapping
	public ResponseEntity<StockDto> save(@RequestBody StockDto dto, UriComponentsBuilder uriBuilder) throws URISyntaxException{
		
		if(cache.isEmpty()) {
			logger.info("stock cache is empty and will be populated");
			cache.populate(stockManagerClient.getStocks());
		}
		
		logger.info("getting stock in cache");
		if(cache.exists(dto.getId())) {
			Stock stock = new Stock(dto);
			stockRepository.save(stock);
			URI uri = uriBuilder.path("/stock/{id}").buildAndExpand(stock.getId()).toUri();
			return ResponseEntity.created(uri).body(new StockDto(stock));
		}
		
		logger.info(String.format("no stock with id [%s] found in stock service",dto.getId()));
		return ResponseEntity.notFound().build();
	}
	
	
}
