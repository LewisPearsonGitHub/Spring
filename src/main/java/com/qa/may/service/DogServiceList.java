package com.qa.may.service;

import java.util.ArrayList;
import java.util.List;

import com.qa.may.entity.Dog;

public class DogServiceList implements DogService{
	
	private List<Dog> dogs = new ArrayList<>();

	@Override
	public Dog getById(int id) {
		return this.dogs.get(id);
	}

	@Override
	public List<Dog> getAll() {
		return this.dogs;
	}

	@Override
	public Dog findByName(String name) {
		return null;
	}

	@Override
	public Dog create(Dog dog1) {
		this.dogs.add(dog1);
		return this.dogs.get(this.dogs.size() -1);
	}

	@Override
	public Dog update(int id, String name, String breed, Integer age) {
		Dog toUpdate = this.dogs.get(id);
		if (name != null)
			toUpdate.setName(name);
		if (breed != null)
			toUpdate.setBreed(breed);
		if (age != null)
			toUpdate.setAge(age);
		
		return toUpdate;
	}

	@Override
	public void delete(int id) {
		this.dogs.remove(id);
		
	}

}
