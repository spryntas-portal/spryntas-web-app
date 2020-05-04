package com.spryntas.service;

import java.util.List;

import com.spryntas.domain.User;

public interface UserService {
	
	List<User> getAllUser();

	User getUserByEmail(String email);

	User saveUser(User user) throws Exception;

}
