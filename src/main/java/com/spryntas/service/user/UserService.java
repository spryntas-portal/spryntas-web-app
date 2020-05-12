package com.spryntas.service.user;

import java.util.List;

import com.spryntas.model.User;

public interface UserService {
	
	List<User> getAllUser();

	User getUserByEmail(String email);

	User saveUser(User user) throws Exception;

}
