package com.wecp.services;

import com.wecp.entities.LoyaltyPoints;
import com.wecp.entities.User;

public interface LoyaltyPointsService {
	
	public LoyaltyPoints addPoints(User user, double transactionAmount);
	
	

}
