package com.wecp.repos;

import org.springframework.data.jpa.repository.JpaRepository;

import com.wecp.entities.Bank;

import antlr.collections.List;

public interface BankRepository extends JpaRepository<Bank, Long> {
	
	java.util.List<Bank> findAll();

	Bank findByBankName(String bankName);

}
