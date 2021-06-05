package com.inatel.stockquotemanager.cache;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.inatel.stockquotemanager.dto.StockConsumingDto;

@Service
@Scope("singleton")
public class StockCache {
	
	private Map<String, String> cache = new ConcurrentHashMap<>();
	
	public void reset() {
		cache.clear();
	}
	
	public boolean isEmpty() {
		return cache.isEmpty();
	}

	public void populate(StockConsumingDto[] stocks) {
		Arrays.asList(stocks).forEach(stock -> cache.put(stock.getId(), stock.getDescription()));
	}

	public boolean exists(String id) {
		return cache.containsKey(id);
	}

}
