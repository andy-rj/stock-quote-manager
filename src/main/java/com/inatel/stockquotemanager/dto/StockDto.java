package com.inatel.stockquotemanager.dto;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.inatel.stockquotemanager.jpa.entity.Stock;

public class StockDto {
	String id;
	Map<String,String> quotes;
	
	public StockDto (Stock stock) {
		this.id = stock.getId();
		quotes = new HashMap<>();
		if(stock.getQuotes() != null)
			stock.getQuotes().forEach(quote -> quotes.put(quote.getId(), quote.getValor().toPlainString()));
	}
	
	public StockDto() {
		
	}
	
	public static List<StockDto> converter(List<Stock> stocks) {
		return stocks.stream().map(StockDto::new).collect(Collectors.toList());
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Map<String, String> getQuotes() {
		return quotes;
	}
	
}
