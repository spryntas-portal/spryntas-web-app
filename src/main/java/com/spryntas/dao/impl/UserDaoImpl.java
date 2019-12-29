package com.spryntas.dao.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.spryntas.dao.UserDao;
import com.spryntas.domain.User;

@Repository
public class UserDaoImpl implements UserDao{
	
	private static final Logger log = LoggerFactory.getLogger(UserDaoImpl.class);
	
	@Autowired
    private JdbcTemplate jdbcTemplate;

	@Override
	public List<User> getUserList() {

		return jdbcTemplate.query("select * from user", new BeanPropertyRowMapper<User>(User.class));
	}
	
	@Override
	public User getUserByEmail(String email) {
		String sql = "select * from user where email=?;";
		User user = jdbcTemplate.queryForObject(sql, new Object[] {email},
				new BeanPropertyRowMapper<User>(User.class));
		return user;
	}
	
	@Override
	public User saveUser(User user) {
		String sql = "insert into user(username,password,email,user_role)"
				+ " values(?,?,?,?);";
		jdbcTemplate.update(sql, new Object[] {user.getUsername(),user.getPassword(),
				user.getEmail(),user.getUserRole().getId()});
		return getUserByEmail(user.getEmail())!=null?getUserByEmail(user.getEmail()):null;
	}

}
