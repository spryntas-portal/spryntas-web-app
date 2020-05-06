package com.spryntas.api.user;

import java.util.List;

import com.spryntas.model.User;

public interface UserDao {
	
	List<User> getUserList();

	User getUserByEmail(String email);

	User saveUser(User user) throws Exception;

}
