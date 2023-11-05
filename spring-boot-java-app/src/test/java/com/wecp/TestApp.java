package com.wecp;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.wecp.controller.UserTransactionController;
import com.wecp.entities.PersonTransaction;
import com.wecp.entities.TxnType;

@AutoConfigureMockMvc
@SpringBootTest
public class TestApp {
	
	
	@Autowired
	UserTransactionController userTransactionController;
	
	@Autowired
	MockMvc mockMvc;
	
	ObjectMapper mapper = new ObjectMapper();
	
	@Test
	public void testCreatePersonTransactionUser() {
		 try {
			 PersonTransaction personTransaction = new PersonTransaction();
			 personTransaction.setPersonName("test user");
			 personTransaction.setTransactionAmount(100d);
			 personTransaction.setTransactionType(TxnType.CASH.getType());
				
			String json = mapper.writeValueAsString(personTransaction);
			ResultActions actions =   mockMvc.perform(MockMvcRequestBuilders.post("/addOrUpdate")
						.content(json)
						.contentType(MediaType.APPLICATION_JSON)
			            .accept(MediaType.ALL))
			            .andExpect(status().isOk());
		
		} catch (Exception e) {
			assertEquals(true, false);
		}
		           // .andExpect(content().string(null));
	}
	
	@Test
	public void testUpdatePersonTransactionUser() {
		 try {
			
			MvcResult mvcResult =   mockMvc.perform(MockMvcRequestBuilders.get("/fetchAllPersonTransactions")
						.contentType(MediaType.APPLICATION_JSON)
			            .accept(MediaType.ALL))
						.andExpect(status().isOk())
			            .andReturn();
			String response = mvcResult.getResponse().getContentAsString();
		
			PersonTransaction[] txns =  mapper.readValue(response, PersonTransaction[].class);
			
			PersonTransaction toUpdate = txns[0];
			String json = mapper.writeValueAsString(toUpdate);
			int countOfRecords = txns.length;
			
			ResultActions actions =   mockMvc.perform(MockMvcRequestBuilders.post("/addOrUpdate")
						.content(json)
						.contentType(MediaType.APPLICATION_JSON)
			            .accept(MediaType.ALL))
			            .andExpect(status().isOk());
			
			mvcResult =   mockMvc.perform(MockMvcRequestBuilders.get("/fetchAllPersonTransactions")
					.contentType(MediaType.APPLICATION_JSON)
		            .accept(MediaType.ALL))
					.andExpect(status().isOk())
		            .andReturn();
			response = mvcResult.getResponse().getContentAsString();
	
		PersonTransaction[] txns2 =  mapper.readValue(response, PersonTransaction[].class);
		
			if(countOfRecords == txns2.length) {
				assertEquals(true, true);//update success in a way its updating and not creating
			}
			else {
				assertEquals(true, false);
			}
			
		} catch (Exception e) {
			assertEquals(true, false);
		}
		           // .andExpect(content().string(null));
	}
	
	
	@Test
	public void testFetchingAllPersonTxns() {
		 try {
			mockMvc.perform(MockMvcRequestBuilders.get("/fetchAllPersonTransactions")
			            .accept(MediaType.ALL))
			            .andExpect(status().isOk());
		} catch (Exception e) {
			assertEquals(true, false);
		}
		           // .andExpect(content().string(null));
	}
	
	@Test
	public void testCalculateTotalSales() {
		 try {
			mockMvc.perform(MockMvcRequestBuilders.get("/calculateTotalSales")
			            .accept(MediaType.ALL))
			            .andExpect(status().isOk());
		} catch (Exception e) {
			assertEquals(true, false);
		}
		           // .andExpect(content().string(null));
	}
	
	@Test
	public void testCalculateTotalCreditCardSales() {
		 try {
			mockMvc.perform(MockMvcRequestBuilders.get("/calculateTotalCreditCardSales")
			            .accept(MediaType.ALL))
			            .andExpect(status().isOk());
		} catch (Exception e) {
			assertEquals(true, false);
		}
		           // .andExpect(content().string(null));
	}
	
	@Test
	public void testCalculateTotalCashSales() {
		 try {
			mockMvc.perform(MockMvcRequestBuilders.get("/calculateTotalCashSales")
			            .accept(MediaType.ALL))
			            .andExpect(status().isOk());
		} catch (Exception e) {
			assertEquals(true, false);
		}
		           // .andExpect(content().string(null));
	}
	
	@Test
	public void testFetchPersonWithMostSales() {
		 try {
			mockMvc.perform(MockMvcRequestBuilders.get("/fetchPersonWithMostSales")
			            .accept(MediaType.ALL))
			            .andExpect(status().isOk());
		} catch (Exception e) {
			assertEquals(true, false);
		}
		           // .andExpect(content().string(null));
	}
	
	@Test
	public void testDeletePersonTransactionRecord() {
		 try {
			
			MvcResult mvcResult =   mockMvc.perform(MockMvcRequestBuilders.get("/fetchAllPersonTransactions")
						.contentType(MediaType.APPLICATION_JSON)
			            .accept(MediaType.ALL))
						.andExpect(status().isOk())
			            .andReturn();
			String response = mvcResult.getResponse().getContentAsString();
		
			PersonTransaction[] txns =  mapper.readValue(response, PersonTransaction[].class);
			
			PersonTransaction toDelete = txns[0];
			String json = mapper.writeValueAsString(toDelete);
			int countOfRecords = txns.length;
			
			ResultActions actions =   mockMvc.perform(MockMvcRequestBuilders.post("/delete?id="+toDelete.getId())
						.content(json)
						.contentType(MediaType.APPLICATION_JSON)
			            .accept(MediaType.ALL))
			            .andExpect(status().isOk());
			
			mvcResult =   mockMvc.perform(MockMvcRequestBuilders.get("/fetchAllPersonTransactions")
					.contentType(MediaType.APPLICATION_JSON)
		            .accept(MediaType.ALL))
					.andExpect(status().isOk())
		            .andReturn();
			response = mvcResult.getResponse().getContentAsString();
	
		PersonTransaction[] txns2 =  mapper.readValue(response, PersonTransaction[].class);
		
			if((countOfRecords-1) == txns2.length) {
				assertEquals(true, true);//delete success
			}
			else {
				assertEquals(true, false);
			}
			
		} catch (Exception e) {
			assertEquals(true, false);
		}
		           // .andExpect(content().string(null));
	}
	

}
