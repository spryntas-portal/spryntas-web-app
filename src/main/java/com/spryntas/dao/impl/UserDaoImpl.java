package com.spryntas.dao.impl;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.spryntas.dao.UserDao;
import com.spryntas.domain.User;

@Repository(value="userDao")
public class UserDaoImpl implements UserDao{
	
	private static final Logger LOGGER = LogManager.getLogger(UserDaoImpl.class);
	
	@Autowired
    private JdbcTemplate jdbcTemplate;

	@Override
	public List<User> getUserList() {
		LOGGER.info("Retriving all user details");

		return jdbcTemplate.query("select user_id,username,email,user_role,user_type,user_status from user", new BeanPropertyRowMapper<User>(User.class));
	}
	
	@Override
	public User getUserByEmail(String email) {
		LOGGER.info("Retrieving user details by email");
		String sql = "select user_id,username,password,email,user_role,user_type,user_status from user where email=?;";
		User user = jdbcTemplate.queryForObject(sql, new Object[] {email},
				new BeanPropertyRowMapper<User>(User.class));
		return user;
	}
	
	@Override
	public User saveUser(User user) {
		LOGGER.info("Registering user by given signup info");
		String sql = "insert into user(username,password,email,user_role)"
				+ " values(?,?,?,?);";
		jdbcTemplate.update(sql, new Object[] {user.getUsername(),user.getPassword(),
				user.getEmail(),user.getUserRole().getId()});
		return getUserByEmail(user.getEmail())!=null?getUserByEmail(user.getEmail()):null;
	}

}
