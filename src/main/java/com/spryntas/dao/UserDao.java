package com.spryntas.dao;

import java.util.List;

import com.spryntas.domain.User;

public interface UserDao {
	
	List<User> getUserList();

	User getUserByEmail(String email);

	User saveUser(User user) throws Exception;

}
