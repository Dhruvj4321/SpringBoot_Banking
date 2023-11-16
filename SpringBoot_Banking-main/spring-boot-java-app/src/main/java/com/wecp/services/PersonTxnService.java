package com.wecp.services;

import java.util.List;

import com.wecp.dto.PersonSalesDto;
import com.wecp.entities.PersonTransaction;

public interface PersonTxnService {
	
	public PersonTransaction saveOrUpdate(PersonTransaction personTransaction);
	
	public void delete(Long id);
	
	public List<PersonTransaction> fetchAll();
	
	public Double calculateTotalSales();
    
    
    public Double calculateTotalCreditCardSales();
    
    public Double calculateTotalCashSales();
    
    
    public PersonSalesDto fetchPersonWithMostSales();

}
