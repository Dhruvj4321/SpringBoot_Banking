package com.wecp.repos;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.wecp.dto.PersonSalesDto;
import com.wecp.entities.PersonTransaction;

public interface PersonTxnRepository extends CrudRepository<PersonTransaction, Long> {
	
    @Query(value = "select sum(transaction_amount) from person_transaction", nativeQuery = true)
   public Double calculateTotalSales();
    
    
    @Query(value = "select sum(transaction_amount) from person_transaction where transaction_type = 'CREDIT_CARD'", nativeQuery = true)
    public Double calculateTotalCreditCardSales();
    
    @Query(value = "select sum(transaction_amount) from person_transaction where transaction_type = 'CASH'", nativeQuery = true)
    public Double calculateTotalCashSales();
    
    
    @Query(value = "select new com.wecp.dto.PersonSalesDto(p.personName, sum(p.transactionAmount) as total) from PersonTransaction p group by p.personName order by total desc")
    public List<PersonSalesDto> fetchPersonWithMostSales();

   
}