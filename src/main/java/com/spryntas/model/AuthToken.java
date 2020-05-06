package com.spryntas.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class AuthToken {

	private String token;
	private String email;

	public AuthToken(String token, String email) {
		this.token = token;
		this.email = email;
	}

}
