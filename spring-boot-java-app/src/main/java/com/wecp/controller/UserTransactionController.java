package com.wecp.controller;

import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.wecp.entities.LoyaltyPoints;
import com.wecp.entities.User;
import com.wecp.entities.UserTransaction;
import com.wecp.repos.LoyaltyPointsRepository;
import com.wecp.repos.UserRepository;
import com.wecp.repos.UserTransactionRepository;
import com.wecp.services.LoyaltyPointsServiceImpl;

@RestController
//@CrossOrigin()
public class UserTransactionController {
	@Autowired
	UserTransactionRepository userTransactionRepository;
	
	@Autowired
	UserRepository userRepository;
	
	@Autowired
	LoyaltyPointsServiceImpl pointsImpl;
	
	@Autowired
	LoyaltyPointsRepository loyaltyPointsRepository;
	
	@RequestMapping(value = "/transaction", method = RequestMethod.POST)
	@Transactional
	public ResponseEntity<?> transact(@RequestBody UserTransaction transaction)
			throws Exception {

		Objects.requireNonNull(transaction);
		Objects.requireNonNull(transaction.getUserId());
		Objects.requireNonNull(transaction.getTransactionType());
		Objects.requireNonNull(transaction.getTransactionAmount());
		User uswr = userRepository.findByUserId(transaction.getUserId());
		Objects.requireNonNull(uswr);
		transaction.setUser(uswr);
		
		
		
			if(transaction.getTransactionType().equals("CREDIT")) {
				userTransactionRepository.save(transaction);
				uswr.setOustanding(uswr.getOustanding() + transaction.getTransactionAmount());
				userRepository.save(uswr);
			}
			else if(transaction.getTransactionType().equals("DEBIT")){
				if(uswr.getOustanding() == null) {
					throw new Exception("NOT_ENOUGH_BALANCE");
				}
				
				if(uswr.getOustanding() < transaction.getTransactionAmount()) {
					throw new Exception("NOT_ENOUGH_BALANCE");
				}
				
				uswr.setOustanding(uswr.getOustanding() - transaction.getTransactionAmount());
				userRepository.save(uswr);
				userTransactionRepository.save(transaction);
			}
			
		pointsImpl.addPoints(uswr, transaction.getTransactionAmount());
		
		return ResponseEntity.ok("success, Loyalty Points also added");
	}
	
	@RequestMapping(value = "/all-transactions", method = RequestMethod.GET)
	@Transactional
	public ResponseEntity<?>  alltransactions(@RequestParam(name = "userId") String userId){
		Objects.requireNonNull(userId);
		User uswr = userRepository.findByUserId(userId);
			if(uswr == null) {
				return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
			}
		Long uid =  userRepository.findByUserId(userId).getId();
		List<UserTransaction> txns =  userTransactionRepository.findByUserId(uid);
		return ResponseEntity.ok(txns);
	}
	
	@RequestMapping(value = "/out-standing", method = RequestMethod.GET)
	@PreAuthorize("hasRole('CLIENT')")
	@Transactional
	public ResponseEntity<?>  getOutstanding(@RequestParam(name = "userId") String userId){
		Objects.requireNonNull(userId);
		User uswr = userRepository.findByUserId(userId);
			if(uswr == null) {
				return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
			}
		
		return ResponseEntity.ok(uswr.getOustanding());
	}
	
	@GetMapping("/fetchAllPoints")
    public ResponseEntity<List<LoyaltyPoints>> getAllLoyaltyPoints() {
        List<LoyaltyPoints> loyaltyPoints = loyaltyPointsRepository.findAll();
        return ResponseEntity.ok(loyaltyPoints);
    }

    @GetMapping("/pointsByUser")
    public ResponseEntity<List<LoyaltyPoints>> getLoyaltyPointsByUserId(@RequestParam(name = "userId") String userId) {
        List<LoyaltyPoints> loyaltyPoints = loyaltyPointsRepository.findByUserUserId(userId);
        return ResponseEntity.ok(loyaltyPoints);
    }

}
