package com.wecp.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wecp.dto.PersonSalesDto;
import com.wecp.entities.PersonTransaction;
import com.wecp.entities.TxnType;
import com.wecp.repos.PersonTxnRepository;

@Service
@Transactional
public class PersonTxnServiceImpl implements PersonTxnService{
	@Autowired
	PersonTxnRepository repository;

	@Override
	public PersonTransaction saveOrUpdate(PersonTransaction personTransaction) {
		// TODO Auto-generated method stub
		
		PersonTransaction personTransaction2 = null;
		if(personTransaction.getId() != null) {
			 personTransaction2 = repository.findById(personTransaction.getId()).get();
		}
		Objects.requireNonNull(personTransaction.getPersonName());
		Objects.requireNonNull(personTransaction.getTransactionAmount());
		Objects.requireNonNull(personTransaction.getTransactionType());
		if(TxnType.valueOf(personTransaction.getTransactionType()) == null) {
			throw new RuntimeException("INVALID_TRANSACTION_TYPE");
		}
		
		if(personTransaction2 == null) {
			return repository.save(personTransaction);
		}
		else {
			personTransaction2.setPersonName(personTransaction.getPersonName());
			personTransaction2.setTransactionAmount(personTransaction.getTransactionAmount());
			personTransaction2.setTransactionType(personTransaction.getTransactionType());
			return repository.save(personTransaction2);
		}
	}

	@Override
	public void delete(Long id) {
		// TODO Auto-generated method stub
		repository.deleteById(id);
	}

	@Override
	public List<PersonTransaction> fetchAll() {
		// TODO Auto-generated method stub
		Iterable<PersonTransaction>  itr = repository.findAll();
		List<PersonTransaction> list = new ArrayList<>();
		itr.forEach(list::add);
		return list;
	}

	@Override
	public Double calculateTotalSales() {
		// TODO Auto-generated method stub
		return repository.calculateTotalSales();
	}

	@Override
	public Double calculateTotalCreditCardSales() {
		// TODO Auto-generated method stub
		return repository.calculateTotalCreditCardSales();
	}

	@Override
	public Double calculateTotalCashSales() {
		// TODO Auto-generated method stub
		return repository.calculateTotalCashSales();
	}

	@Override
	public PersonSalesDto fetchPersonWithMostSales() {
		// TODO Auto-generated method stub
	List<PersonSalesDto> list = 	repository.fetchPersonWithMostSales();
		return list.get(0);
	}

}
