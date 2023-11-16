package com.wecp.services;

import org.springframework.stereotype.Service;

import com.wecp.entities.Bank;

import java.util.List;

@Service
public interface BankService {
	
	public Bank createOrUpdate(Bank bank);
	
	public List<Bank> fetchAll();
	
	
	public Bank findByBankName(String bankName);
	
	public void delete(long id);

}
