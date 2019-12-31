package com.spryntas.api;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@Api(value="testAPi")
public class TestApi {
	
	private static final Logger LOGGER = LogManager.getLogger(UserApi.class);
	
	@ApiOperation(value = "Testing API")
	@GetMapping("/")
	public String hello() {
		LOGGER.info("Started Test API endpoint");
		return "Its Working...";
	}

}
