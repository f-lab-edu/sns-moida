package com.moida.request;

import javax.validation.constraints.NotBlank;

import com.moida.response.LoginResponse;

import lombok.Builder;
import lombok.Getter;
import lombok.Value;

@Getter
@Value
public class LoginRequest {

	@NotBlank(message = "아이디를 입력해주세요")
	String userId;

	@NotBlank(message = "비밀번호를 입력해주세요")
	String password;

	@Builder
	public LoginRequest(String userId, String password) {
		this.userId = userId;
		this.password = password;
	}

	public LoginResponse toResponse() {
		return new LoginResponse(
			this.userId,
			this.password);
	}
}
