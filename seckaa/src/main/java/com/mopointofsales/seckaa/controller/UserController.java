package com.mopointofsales.seckaa.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mopointofsales.seckaa.entity.User;
import com.mopointofsales.seckaa.exception.ResourceNotFoundException;
import com.mopointofsales.seckaa.repository.UserRepository;

import jakarta.websocket.server.PathParam;

@RestController
@RequestMapping("/api/users")
public class UserController {
	@Autowired private UserRepository userRepository;
	
	//get all user
	
	@GetMapping
	public List<User> getAllUser(){
		
		return this.userRepository.findAll();
	}
	//get user by id
	
	@GetMapping("/{id}")
	public User getUserById(@PathVariable(value = "id") long userId) {
		return this.userRepository.findById(userId)
				.orElseThrow(()-> new ResourceNotFoundException("user not found with Id : "+userId));
	}
	//create user
	
	@PostMapping
	public User createUser(@RequestBody User user) {
		return this.userRepository.save(user);
	}
	
	//update user
	@PutMapping("/{id}")
	public User updateUser(
			@RequestBody User user,
			@PathVariable("id") long userId) {
		
		User existingUser = this.userRepository.findById(userId)
		.orElseThrow(()-> new ResourceNotFoundException("user not found with Id : "+userId));
		existingUser.setFirstName(user.getFirstName());
		existingUser.setLastName(user.getLastName());
		existingUser.setEmail(user.getEmail());
		
		return this.userRepository.save(existingUser);
	}
	//delete user by id 
	@DeleteMapping("/{id}")
	public ResponseEntity<User> deleteUser(@PathVariable("id") long userId){
		User existingUser = this.userRepository.findById(userId)
				.orElseThrow(()-> new ResourceNotFoundException("user not found with Id : "+userId));
		 this.userRepository.delete(existingUser);
		 return ResponseEntity.ok().build();
	}
}
