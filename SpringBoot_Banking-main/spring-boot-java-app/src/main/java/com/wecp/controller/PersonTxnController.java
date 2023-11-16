package com.wecp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.wecp.dto.PersonSalesDto;
import com.wecp.entities.PersonTransaction;
import com.wecp.repos.PersonTxnRepository;
import com.wecp.services.PersonTxnService;
@RestController
public class PersonTxnController {
	@Autowired
	PersonTxnService personTxnService;
	
	@Autowired
	PersonTxnRepository personTxnRepository;
	
	@RequestMapping(value = "/addOrUpdate", method = RequestMethod.POST)
	public ResponseEntity<?> addOrUpdate(@RequestBody PersonTransaction personTransaction)
			throws Exception {

		personTxnService.saveOrUpdate(personTransaction);
		return ResponseEntity.ok("success");
	}
	
	
	@RequestMapping(value = "/delete", method = RequestMethod.POST)
	public ResponseEntity<?> delete(@RequestParam("id") Long id){
		if(!personTxnRepository.findById(id).isPresent()) {
			return ResponseEntity.ok("INVALID_ID_PASSED");
		}

		personTxnService.delete(id);
		return ResponseEntity.ok("success");
	}
	
	
	
	@RequestMapping(value = "/fetchAllPersonTransactions", method = RequestMethod.GET)
	public ResponseEntity<?> fetchAllPersonTransactions()
			throws Exception {

		return ResponseEntity.ok(personTxnService.fetchAll());
	}
	
	
	@RequestMapping(value = "/calculateTotalSales", method = RequestMethod.GET)
	public ResponseEntity<?> calculateTotalSales()
			throws Exception {
		Double amt = personTxnService.calculateTotalSales() ;
		return ResponseEntity.ok(amt == null?0d:amt);
	}
	

	@RequestMapping(value = "/calculateTotalCreditCardSales", method = RequestMethod.GET)
	public ResponseEntity<?> calculateTotalCreditCardSales()
			throws Exception {

		Double amt = personTxnService.calculateTotalCreditCardSales();
		return ResponseEntity.ok(amt == null?0d:amt);
	}
	

	@RequestMapping(value = "/calculateTotalCashSales", method = RequestMethod.GET)
	public ResponseEntity<?> calculateTotalCashSales()
			throws Exception {

		Double amt = personTxnService.calculateTotalCashSales();
		return ResponseEntity.ok(amt == null?0d:amt);
	}
	
	@RequestMapping(value = "/fetchPersonWithMostSales", method = RequestMethod.GET)
	public ResponseEntity<?> fetchPersonWithMostSales()
			throws Exception {

		PersonSalesDto personSalesDto = personTxnService.fetchPersonWithMostSales();
		return ResponseEntity.ok(personSalesDto == null?"NA":personSalesDto);
	}

}
