package com.spryntas.api.user;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.spryntas.exception.BadRequestException;
import com.spryntas.exception.UnAuthorizedException;
import com.spryntas.model.User;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/user")
@Api(value="users",description = "User registeration and login operations")
@Slf4j
public class UserApi {
	
	@Autowired
	UserService userService;
	 
	@PreAuthorize("hasRole('ADMIN')")
	@ApiOperation(value = "Retrieve all user details")
	@GetMapping("all")
	public List<User> getAllUsers() {
		log.info("Started getAllUser endpoint");
		return userService.getAllUser();
	}
	
	@PreAuthorize("hasAnyRole('USER', 'ADMIN')")
	@ApiOperation(value = "Retrieve user detail by given emailId")
	@GetMapping()
	public User getUserByEmail(Authentication authentication) {
		log.info("Started getUserByEmail endpoint");
		if(authentication == null)
			throw new UnAuthorizedException("Invalid Authentication");
		return userService.getUserByEmail(authentication.getName());
	}
	
	@PreAuthorize("hasAnyRole('USER', 'ADMIN', 'ANONYMOUS')")
	@ApiOperation(value = "Register user detail with signup user details")
	@PostMapping("/signup")
	public User createNewUser(@RequestBody User userDetail) throws Exception {
		log.info("Started createNewUser endpoint");
		User user = null;
		try {
			user = userService.saveUser(userDetail);
		}catch(DataIntegrityViolationException ex) {
			log.error("User with this emailId already exist"+ex.getMessage());
			throw new BadRequestException("User with this email already exist");
		}
		return user;
	}

}
