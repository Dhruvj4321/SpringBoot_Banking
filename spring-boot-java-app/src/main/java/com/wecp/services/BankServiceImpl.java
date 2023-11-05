package com.wecp.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wecp.entities.Bank;
import com.wecp.repos.BankRepository;

import java.util.List;

@Service
public class BankServiceImpl implements BankService {
	

	@Autowired
	BankRepository repository;

	@Override
	public Bank createOrUpdate(Bank bank) {
		Bank existingBank = repository.findByBankName(bank.getBankName());
		
		if (existingBank != null)
		{
			existingBank.setBankLocation(bank.getBankLocation());
			return repository.save(existingBank);
		}
		else
		{
			return repository.save(bank);
		}
		
		
		
	}

	@Override
	public List<Bank> fetchAll() {
		// TODO Auto-generated method stub
		return repository.findAll();
	}

	@Override
	public void delete(long id) {
		// TODO Auto-generated method stub
		repository.deleteById(id);
	}

	
	@Override
	public Bank findByBankName(String bankName) {
		// TODO Auto-generated method stub
		return repository.findByBankName(bankName);
	}

	
	
	

}
