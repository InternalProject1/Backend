package com.prodapt.registration.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.prodapt.registration.model.User;
import com.prodapt.registration.service.UserService;

@RestController
@RequestMapping("/user")
public class UserController {

	@Autowired
	private UserService userService;
	
	@PostMapping("/registerUser")
	public Map<String, Object> createOrRegisterUser(@RequestBody User user) {
		Map<String, Object> map = new HashMap<>();
		user = userService.save(user);
		if (user != null && user.getId() != null && user.getId() != 0) {
			map.put("status", "SUCCESS");
			map.put("message", "User created successfully");
			map.put("user", user);
		} else {
			map.put("status", "FAILURE");
			map.put("message", "User creation failed");
			map.put("user", user);
		}
		return map;
	}
	
	@PostMapping("/verfiyUser")
	public Map<String, Object> verifyUserByUsername(@RequestBody User user) {
		Map<String, Object> map = new HashMap<>();
		Boolean userExists = userService.verifyUserByUsername(user.getUsername());
		if (userExists) {
			map.put("status", "SUCCESS");
			map.put("message", "User exists!");
		} else {
			map.put("status", "FAILURE");
			map.put("message", "User doesn't exists");
		}
		return map;
	}
}
