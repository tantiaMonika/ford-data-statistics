package com.datastatistics.ford.domain;


import java.math.BigDecimal;
import java.util.Date;

public class FordStockData {

	private Date date;
	private Integer year;
	private Integer month;
	private Integer day;
	private BigDecimal open;
	private BigDecimal close;

	public Integer getYear() {
		return year;
	}

	public void setYear(Integer year) {
		this.year = year;
	}

	public Integer getMonth() {
		return month;
	}

	public void setMonth(Integer month) {
		this.month = month;
	}

	public Integer getDay() {
		return day;
	}

	public void setDay(Integer day) {
		this.day = day;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public BigDecimal getOpen() {
		return open;
	}

	public void setOpen(BigDecimal open) {
		this.open = open;
	}

	public BigDecimal getClose() {
		return close;
	}

	public void setClose(BigDecimal close) {
		this.close = close;
	}

	public FordStockData(com.datastatistics.ford.entity.FordStockData entity) {
		this.date = entity.getDate();
		this.year = entity.getYear();
		this.month = entity.getMonth();
		this.day = entity.getDay();
		this.open = entity.getOpen();
		this.close = entity.getClose();
	}

	public FordStockData() {
		
	}

	public com.datastatistics.ford.domain.api.FordStockData toApi() {
		com.datastatistics.ford.domain.api.FordStockData api = new com.datastatistics.ford.domain.api.FordStockData();
		api.setDate(this.getDate());
		api.setYear(this.getYear());
		api.setMonth(this.getMonth());
		api.setDay(this.getDay());
		api.setOpen(this.getOpen());
		api.setClose(this.getClose());
		return api;
	}

}
