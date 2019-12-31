package com.spryntas.api;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
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

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@Api(value="AuthenticationApi", description="To authenticate the token info")
@RequestMapping("/token")
public class AuthenticationApi {

	private static final Logger LOGGER = LogManager.getLogger(UserApi.class);
	
	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private JwtTokenHelper jwtTokenHelper;

	@Autowired
	private UserService userService;

	@ApiOperation(value = "Generate token for user authentication")
	@PostMapping()
	public AuthToken register(@RequestBody LoginUser loginUser) throws AuthenticationException {

		LOGGER.info("Started generating authToken for given user in auth endpoint");
		authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(loginUser.getEmail(), loginUser.getPassword()));
		final User user = userService.getUserByEmail(loginUser.getEmail());
		final String token = jwtTokenHelper.generateToken(user);
		return new AuthToken(token, user.getEmail());
	}
}
