package com.inatel.stockquotemanager.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.inatel.stockquotemanager.cache.StockCache;


@RestController
@RequestMapping("/stockcache")
public class StockCacheController {
	
	@Autowired
	private StockCache cache;

	@DeleteMapping
	public ResponseEntity<?> list(){
		cache.reset();
		return ResponseEntity.ok().build();
	}
}
