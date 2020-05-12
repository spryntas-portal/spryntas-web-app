package com.spryntas.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.spryntas.config.JwtTokenHelper;
import com.spryntas.model.AuthToken;
import com.spryntas.model.LoginUser;
import com.spryntas.model.User;
import com.spryntas.service.user.UserService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;

@RestController
@Api(value="AuthenticationApi", description="To authenticate the token info")
@RequestMapping("/session")
@Slf4j
public class SessionApi {

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private JwtTokenHelper jwtTokenHelper;

	@Autowired
	private UserService userService;

	@ApiOperation(value = "Generate token for user authentication")
	@PostMapping()
	public AuthToken createToken(@RequestBody LoginUser loginUser) throws AuthenticationException {

		log.info("Started generating authToken for given user in auth endpoint");
		Authentication auth = authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(loginUser.getEmail(), loginUser.getPassword()));
		final User user = userService.getUserByEmail(loginUser.getEmail());
		final String token = jwtTokenHelper.generateToken(auth);
		return new AuthToken(token, user.getEmail());
	}
}
