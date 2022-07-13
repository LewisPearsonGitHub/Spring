package com.qa.may.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import com.qa.may.entity.Dog;
import com.qa.may.repo.DogRepo;

@Service
@Primary
public class DogServiceDB implements DogService{
	
	@Autowired
	private DogRepo repo;

	@Override
	public Dog getById(int id) {
		return this.repo.findById(id).get();
	}

	@Override
	public List<Dog> getAll() {
		return this.repo.findAll();
	}

	@Override
	public Dog findByName(String name) {
		return this.repo.findByNameStartingWithIgnoreCase(name);
	}

	@Override
	public Dog create(Dog dog1) {
		return this.repo.save(dog1);
	}

	@Override
	public Dog update(int id, String name, String breed, Integer age) {
		Dog toUpdate = this.getById(id);
		if (name != null)
			toUpdate.setName(name);
		if (breed != null)
			toUpdate.setBreed(breed);
		if (age != null)
			toUpdate.setAge(age);
		
		return this.repo.save(toUpdate);
	}

	@Override
	public void delete(int id) {
		this.repo.deleteById(id);
		
	}

}
