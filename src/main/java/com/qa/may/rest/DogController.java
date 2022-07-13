package com.qa.may.rest;

import java.util.List;

import javax.websocket.server.PathParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.qa.may.entity.Dog;
import com.qa.may.service.DogService;

@CrossOrigin
@RestController
public class DogController {
	
	@Autowired
	private DogService service;
	
	@GetMapping("/getDogs")
	public List<Dog> getAll() {
		return this.service.getAll();
	}
	
	@GetMapping("/getDog/{id}")
	public Dog getById(@PathVariable int id) {
		return this.service.getById(id);
	}
	
//	@GetMapping("/demoDog")
//	public Dog getDemoDog() {
//		return new Dog("Rosie", "Cavalier King Charles Spaniel", 6);
//	  }
	
	@PostMapping("/createDog")
	public ResponseEntity<Dog> create(@RequestBody Dog dog1) {
		System.out.println(dog1);
		Dog created = this.service.create(dog1);
		
		return new ResponseEntity<Dog>(created, HttpStatus.CREATED);		
	}
	
	@PatchMapping("/updateDog/{id}")
	public Dog update(@PathVariable("id") int id, @PathParam("name") String name, @PathParam("breed") String breed, @PathParam("age") Integer age) {
		return this.service.update(id, name, breed, age);
	}
	
	@DeleteMapping("deleteDog/{id}")
	public ResponseEntity<?> delete(@PathVariable int id ) {
		this.service.delete(id);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	} 
}
