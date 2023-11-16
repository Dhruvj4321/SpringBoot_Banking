package com.wecp.repos;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.wecp.entities.LoyaltyPoints;
import com.wecp.entities.User;

public interface LoyaltyPointsRepository extends JpaRepository<LoyaltyPoints, Long> {
	
	List<LoyaltyPoints> findByUserUserId(String userId);
	
	LoyaltyPoints findByUser(User user);
	
}
