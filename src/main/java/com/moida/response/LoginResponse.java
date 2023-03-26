package com.moida.response;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class LoginResponse {

	private String userId;

	private String password;

	private String accessToken;

	@Builder
	public LoginResponse(String userId, String password, String accessToken) {
		this.userId = userId;
		this.password = password;
		this.accessToken = accessToken;
	}
}
