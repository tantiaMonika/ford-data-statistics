package com.datastatistics.ford.entity;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.*;

/**
 * This class serves as Entity for the application, corresponding database 
 * table for the same is Daily_Stock_Price
 * @author Monika Tantia
 *
 */
@Table(name = "Daily_Stock_Price")
@Entity
public class FordStockData {

	@Id
	@Column(name = "Date")
	private Date date;
	@Column(name = "Year")
	private Integer year;
	@Column(name = "Month")
	private Integer month;
	@Column(name = "Day")
	private Integer day;

	@Column(name = "Stock_Open")
	private BigDecimal open;

	@Column(name = "Stock_Close")
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

	public FordStockData(Date date, Integer year, Integer month, Integer day, BigDecimal open, BigDecimal close) {
		super();
		this.date = date;
		this.year = year;
		this.month = month;
		this.day = day;
		this.open = open;
		this.close = close;
	}
	
	public FordStockData() {
		
	}


}
