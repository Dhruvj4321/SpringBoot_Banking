package com.wecp.controller;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.wecp.entities.Bank;
import com.wecp.repos.BankRepository;
import com.wecp.services.BankService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

@RestController
@RequestMapping("/banks")
public class BankController {
	
	@Autowired
	BankService bankService;
	
	@Autowired
	BankRepository repository;
	
	@PostMapping
	public Bank createOrUpdate(@RequestBody Bank bank)
	{
		return bankService.createOrUpdate(bank);
	}
	
	@GetMapping
	public List<Bank> getAllBanks()
	{
		List<Bank> bankList = bankService.fetchAll();
		return bankList;
		
	}
	
	@GetMapping("/{bankName}")
	public Bank getBankByName(@PathVariable String bankName)
	{
		return bankService.findByBankName(bankName);
		
	}
	
	@DeleteMapping("/{id}")
	public void deleteBank(@PathVariable long id)
	{
		bankService.delete(id);
		
	}
	
	
	

}
