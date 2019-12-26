package com.spryntas.api;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestApi {
	
	@GetMapping("/")
	public String hello() {
		return "Its Working...";
	}

}
