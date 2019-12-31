package com.spryntas.service.impl;

import java.util.Arrays;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.spryntas.dao.UserDao;
import com.spryntas.domain.User;
import com.spryntas.service.UserService;

@Service(value = "userService")
public class UserServiceImpl implements UserDetailsService, UserService {
	
	private static final Logger LOGGER = LogManager.getLogger(UserServiceImpl.class);

	@Autowired
	UserDao userDao;

	@Autowired
	private BCryptPasswordEncoder bcryptEncoder;

	public List<User> getAllUser() {
		LOGGER.info("starting getAllUser method from userService");
		return userDao.getUserList();
	}

	@Override
	public User getUserByEmail(String email) {
		LOGGER.info("starting getUserByEmail method from userService");
		return userDao.getUserByEmail(email);
	}

	@Override
	public User saveUser(User user) {
		LOGGER.info("starting saveUser method from userService");
		if (user != null && user.getPassword() != null)
			user.setPassword(bcryptEncoder.encode(user.getPassword()));
		return userDao.saveUser(user);
	}

	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		LOGGER.info("starting loadUserByUsername method from userService");
		User user = userDao.getUserByEmail(email);
		if (user == null) {
			throw new UsernameNotFoundException("Invalid username or password.");
		}
		return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(),
				getAuthority());
	}

	private List<SimpleGrantedAuthority> getAuthority() {
		return Arrays.asList(new SimpleGrantedAuthority("ROLE_ADMIN"));
	}

}
