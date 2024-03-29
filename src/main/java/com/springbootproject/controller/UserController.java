package com.springbootproject.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.springbootproject.entity.User;
import com.springbootproject.service.UserService;

@RestController
@RequestMapping("/user")
public class UserController {

	@Autowired
	private UserService userService;
	@GetMapping
	public List<User> getallUsers(){
		return userService.getall();
	}
	
	@PostMapping
	public void createUser(@RequestBody User user) {
		 userService.saveUser(user);
	}
	
	@PutMapping("/{userName}")
	public ResponseEntity<User> findByUserName(@RequestBody User user,
			@PathVariable String userName){
		User byname = userService.findByUsername(userName);
		if(byname != null) {
			byname.setUserName(user.getUserName());
			byname.setPassword(user.getPassword());
			userService.saveUser(byname);
		}
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
	
}
