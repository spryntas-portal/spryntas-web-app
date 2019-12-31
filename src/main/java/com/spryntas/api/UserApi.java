package com.spryntas.api;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.spryntas.domain.User;
import com.spryntas.service.UserService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/user")
@Api(value="users",description = "User registeration and login operations")
public class UserApi {
	
	private static final Logger LOGGER = LogManager.getLogger(UserApi.class);
	
	@Autowired
	UserService userService;
	 
	@ApiOperation(value = "Retrieve all user details")
	@GetMapping("all")
	public List<User> getAllUsers() {
		LOGGER.info("Started getAllUser endpoint");
		return userService.getAllUser();
	}
	
	@ApiOperation(value = "Retrieve user detail by given emailId")
	@GetMapping()
	public User getUserByEmail(@RequestParam("email") String email) {
		LOGGER.info("Started getUserByEmail endpoint");
		return userService.getUserByEmail(email);
	}
	
	@ApiOperation(value = "Register user detail with signup user details")
	@PostMapping("/signup")
	public User saveUser(@RequestBody User user) {
		LOGGER.info("Started saveUser endpoint");
		return userService.saveUser(user);
	}

}
