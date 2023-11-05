package com.wecp.entities;

public enum TxnType {
	
	CREDIT_CARD("CREDIT_CARD"), CASH("CASH");
	
	String type;
	
	private TxnType(String type) {
		this.type = type;
	}

	public String getType() {
		return type;
	}
	
	
	

}
