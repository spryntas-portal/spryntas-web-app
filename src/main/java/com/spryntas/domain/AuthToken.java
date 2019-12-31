package com.spryntas.domain;

public class AuthToken {

	private String token;
	private String email;

	public AuthToken() {

	}

	public AuthToken(String token, String email) {
		this.token = token;
		this.email = email;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String username) {
		this.email = username;
	}

}
