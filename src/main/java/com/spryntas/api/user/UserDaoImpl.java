package com.spryntas.api.user;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.spryntas.model.User;

import lombok.extern.slf4j.Slf4j;

@Repository(value="userDao")
@Slf4j
public class UserDaoImpl implements UserDao{
	
	@Autowired
    private JdbcTemplate jdbcTemplate;

	@Override
	public List<User> getUserList() {
		log.info("Retriving all user details");

		return jdbcTemplate.query("select user_id,username,email,user_role,user_type,user_status from user", new BeanPropertyRowMapper<User>(User.class));
	}
	
	@Override
	public User getUserByEmail(String email) {
		log.info("Retrieving user details by email");
		String sql = "select user_id,username,password,email,user_role,user_type,user_status from user where email=?;";
		User user = jdbcTemplate.queryForObject(sql, new Object[] {email},
				new BeanPropertyRowMapper<User>(User.class));
		return user;
	}
	
	@Override
	public User saveUser(User user) throws Exception{
		log.info("Registering user by given signup info");
		String sql = "insert into user(username,password,email,user_role)"
				+ " values(?,?,?,?);";
		jdbcTemplate.update(sql, new Object[] {user.getUsername(),user.getPassword(),
				user.getEmail(),user.getUserRole().getId()});
		return getUserByEmail(user.getEmail())!=null?getUserByEmail(user.getEmail()):null;
	}

}
