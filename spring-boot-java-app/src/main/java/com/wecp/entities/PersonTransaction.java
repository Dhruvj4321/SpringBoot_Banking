package com.wecp.entities;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
@Entity
public class PersonTransaction {
	
	 @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	 Long id;
	 
	 String personName;
	 
	 Double transactionAmount = 0d;
	 
	 String transactionType = TxnType.CREDIT_CARD.getType();
	 
	 @OneToMany(mappedBy = "personTransaction")
	    private List<Product> products;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getPersonName() {
		return personName;
	}

	public void setPersonName(String personName) {
		this.personName = personName;
	}

	public Double getTransactionAmount() { 
		return transactionAmount;
	}

	public void setTransactionAmount(Double transactionAmount) {
		this.transactionAmount = transactionAmount;
	}

	public String getTransactionType() {
		return transactionType;
	}

	public void setTransactionType(String transactionType) {
		this.transactionType = transactionType;
	}
	 
	 

}
