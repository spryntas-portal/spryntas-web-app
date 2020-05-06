package com.spryntas.api.user;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.spryntas.exception.BadRequestException;
import com.spryntas.model.User;
import com.spryntas.util.enums.UserRolesEnum;

import lombok.extern.slf4j.Slf4j;

@Service(value = "userService")
@Slf4j
public class UserServiceImpl implements UserDetailsService, UserService {
	
	@Autowired
	UserDao userDao;

	@Autowired
	private BCryptPasswordEncoder bcryptEncoder;

	public List<User> getAllUser() {
		log.info("starting getAllUser method from userService");
		return userDao.getUserList();
	}

	@Override
	public User getUserByEmail(String email) {
		log.info("starting getUserByEmail method from userService");
		return userDao.getUserByEmail(email);
	}

	@Override
	public User saveUser(User user) throws Exception {
		log.info("starting saveUser method from userService");
		if (user == null)
			throw new BadRequestException("UserDetail is Empty");
		if (StringUtils.isEmpty(user.getUsername()))
			throw new BadRequestException("Username cannot be Empty");
		if (StringUtils.isEmpty(user.getPassword()))
			throw new BadRequestException("Password cannot be Empty");
		if (user.getPassword() != null)
			user.setPassword(bcryptEncoder.encode(user.getPassword()));
		if (StringUtils.isEmpty(user.getEmail()))
			throw new BadRequestException("Email cannot be Empty");
		if (user.getUserRole() == null)
			throw new BadRequestException("User Role cannot be Empty");
		if (user.getUserRole().equals(UserRolesEnum.UNKNOWN))
			throw new BadRequestException("Invalid User Role");
		return userDao.saveUser(user);
	}

	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		log.info("starting loadUserByUsername method from userService");
		User user = userDao.getUserByEmail(email);
		if (user == null) {
			throw new UsernameNotFoundException("Invalid username or password.");
		}
		return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(),
				getAuthority(user));
	}

	private Set<SimpleGrantedAuthority> getAuthority(User user) {
		Set<SimpleGrantedAuthority> authorities = new HashSet<>();
		//possible to add more than one role to user but not now
		authorities.add( new SimpleGrantedAuthority(user.getUserRole().name()));
		return authorities;
	}

}
