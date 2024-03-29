package com.springbootproject.service;

import java.util.List;
import java.util.Optional;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.springbootproject.entity.User;
import com.springbootproject.repository.UserRepository;

@Component
public class UserService {

	@Autowired
	private UserRepository userRepository;
	
	public void saveUser(User user) {
		userRepository.save(user);
	}
	
	public List<User> getall(){
		return userRepository.findAll();
	}
	
	public Optional<User> findById(ObjectId id) {
		return userRepository.findById(id);
	}
	
	public void deleteByid(ObjectId id) {
		userRepository.deleteById(id);;
	}
	public User findByUsername(String username) {
		return userRepository.findByUserName(username);
	}
}
