package com.moida.response;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class LoginResponse {

	String userId;

	String password;

	@Builder
	public LoginResponse(String userId, String password) {
		this.userId = userId;
		this.password = password;
	}
}
