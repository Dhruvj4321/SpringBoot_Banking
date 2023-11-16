package com.wecp.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wecp.entities.LoyaltyPoints;
import com.wecp.entities.User;
import com.wecp.repos.LoyaltyPointsRepository;

@Service
public class LoyaltyPointsServiceImpl {
	
	@Autowired
	LoyaltyPointsRepository pointsRepository;
	
	public LoyaltyPoints addPoints(User user, double transactionAmount)
	{
		LoyaltyPoints existingLoyaltyPoints = pointsRepository.findByUser(user);
		
		int newPoints;
		
		if(transactionAmount<=200)
		{
			newPoints = 5;
		}
		else if(transactionAmount<=1000)
		{
			newPoints = 10;
		}
		else
		{
			newPoints = 20;
		}
		
		if (existingLoyaltyPoints != null)
		{
			int totalPoints = existingLoyaltyPoints.getValue() + newPoints;
			
			existingLoyaltyPoints.setValue(totalPoints);
			
			return pointsRepository.save(existingLoyaltyPoints);		
		}
		
		else
		{
			LoyaltyPoints loyaltyPoints = new LoyaltyPoints();
			
			loyaltyPoints.setUser(user);
			loyaltyPoints.setValue(newPoints);
			
			return pointsRepository.save(loyaltyPoints);
		}
		
		
	}

}
