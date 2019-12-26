package com.spryntas.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.spryntas.config.JwtTokenHelper;
import com.spryntas.domain.AuthToken;
import com.spryntas.domain.LoginUser;
import com.spryntas.domain.User;
import com.spryntas.service.UserService;

@RestController
@RequestMapping("/token")
public class AuthenticationApi {

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private JwtTokenHelper jwtTokenHelper;

	@Autowired
	private UserService userService;

	@PostMapping("/generate-token")
	public AuthToken register(@RequestBody LoginUser loginUser) throws AuthenticationException {

		authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(loginUser.getUsername(), loginUser.getPassword()));
		final User user = userService.getUserByEmail(loginUser.getUsername());
		final String token = jwtTokenHelper.generateToken(user);
		return new AuthToken(token, user.getEmail());
	}
}
