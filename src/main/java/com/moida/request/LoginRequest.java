package com.moida.request;

import javax.validation.constraints.NotBlank;

import com.moida.config.PasswordEncoder;
import com.moida.domain.User;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
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

	public User toUser() {
		return User.builder()
			.userId(userId)
			.password(password)
			.build();
	}

	public void encryptPassword(PasswordEncoder passwordEncoder) {
		String encryption = passwordEncoder.encrypt(this.password);
		this.password = encryption;
	}
}
