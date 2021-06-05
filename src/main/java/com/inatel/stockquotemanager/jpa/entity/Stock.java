package com.inatel.stockquotemanager.jpa.entity;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.inatel.stockquotemanager.dto.StockDto;

@Entity
public class Stock {
	
	@Id
	private String id;
	private Date dataAtualizacao;
	@OneToMany(mappedBy = "stock", cascade = CascadeType.ALL)
	private List<Quote> quotes;
	
	public Stock() {
		
	}
	
	public Stock(StockDto dto) {
		this.id = dto.getId();
		this.dataAtualizacao = new Date();
		this.quotes = new ArrayList<>();
		if(dto.getQuotes()!=null) {
			dto.getQuotes().forEach((k,v) -> quotes.add(new Quote(k,new BigDecimal(v),this)));
		}
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public Date getDataAtualizacao() {
		return dataAtualizacao;
	}
	public void setDataAtualizacao(Date dataAtualizacao) {
		this.dataAtualizacao = dataAtualizacao;
	}
	public List<Quote> getQuotes() {
		return quotes;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((dataAtualizacao == null) ? 0 : dataAtualizacao.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Stock other = (Stock) obj;
		if (dataAtualizacao == null) {
			if (other.dataAtualizacao != null)
				return false;
		} else if (!dataAtualizacao.equals(other.dataAtualizacao))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
	
	
	
}
