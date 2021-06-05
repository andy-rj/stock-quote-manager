package com.inatel.stockquotemanager.jpa.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.inatel.stockquotemanager.jpa.entity.Stock;

public interface StockRepository extends JpaRepository<Stock, String>{
	
}
