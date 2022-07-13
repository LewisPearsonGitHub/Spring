package com.qa.may.rest;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.Sql.ExecutionPhase;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.ResultMatcher;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.qa.may.entity.Dog;

@SpringBootTest
@AutoConfigureMockMvc
@Sql(scripts = {"classpath:dog-schema.sql", "classpath:dino-data.sql"}, executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
@ActiveProfiles("test")

public class DogControllerIntegrationTest {
	
	@Autowired
	private MockMvc mvc;
	
	@Autowired
	private ObjectMapper mapper;
	
	
	@Test
	void testCreate() throws Exception {
		Dog testDog = new Dog("Rosie", "Cavalier King Charles Spaniel", 6);
		String testDogAsJSON = this.mapper.writeValueAsString(testDog);
		RequestBuilder req = post("/createDog").content(testDogAsJSON).contentType(MediaType.APPLICATION_JSON);
		
		ResultMatcher checkStatus = status().is(201);
		Dog createdDog = new Dog(2, "Rosie", "Cavalier King Charles Spaniel", 6);
		String createdDogAsJSON = this.mapper.writeValueAsString(createdDog);
		ResultMatcher checkBody = content().json(createdDogAsJSON);
		
		this.mvc.perform(req).andExpect(checkStatus).andExpect(checkBody);		
	}

}
