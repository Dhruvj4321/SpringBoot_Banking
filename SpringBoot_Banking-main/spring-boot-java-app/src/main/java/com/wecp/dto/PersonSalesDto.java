package com.wecp.dto;

public class PersonSalesDto {
	
	String personName;
	
	Double totalSales;
	
	

	public PersonSalesDto() {
		
	}

	public PersonSalesDto(String personName, Double totalSales) {
		super();
		this.personName = personName;
		this.totalSales = totalSales;
	}

	public String getPersonName() {
		return personName;
	}

	public void setPersonName(String personName) {
		this.personName = personName;
	}

	public Double getTotalSales() {
		return totalSales;
	}

	public void setTotalSales(Double totalSales) {
		this.totalSales = totalSales;
	}
	
	

}
