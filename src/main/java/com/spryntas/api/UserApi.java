package com.spryntas.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.spryntas.domain.User;
import com.spryntas.service.UserService;

@RestController
@RequestMapping("/user")
public class UserApi {
	
	@Autowired
	UserService userService;
	 
	@GetMapping("all")
	public List<User> getAllUsers() {
		return userService.getAllUser();
	}
	
	@GetMapping()
	public User getUserByEmail(@RequestParam("email") String email) {
		return userService.getUserByEmail(email);
	}
	
	@PostMapping("/signup")
	public User saveUser(@RequestBody User user) {
		return userService.saveUser(user);
	}

}
