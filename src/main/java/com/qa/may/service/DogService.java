package com.qa.may.service;

import java.util.List;

import com.qa.may.entity.Dog;

public interface DogService {
	
	Dog getById(int id);
	
	List<Dog> getAll();
	
	Dog findByName(String name);
	
	Dog create(Dog dog1);
	
	Dog update(int id, String name, String breed, Integer age);
	
	void delete(int id);
}
