package com.moida.response;

import java.sql.Date;

import com.moida.domain.User;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class SignUpResponse {

	String userId;

	String password;

	String email;

	String name;

	String nickname;

	Date birth;

	@Builder
	public SignUpResponse(String userId, String password, String email, String name, String nickname, Date birth) {
		this.userId = userId;
		this.password = password;
		this.email = email;
		this.name = name;
		this.nickname = nickname;
		this.birth = birth;
	}

	public static SignUpResponse from(User user) {
		return SignUpResponse.builder()
			.userId(user.getUserId())
			.password(user.getPassword())
			.email(user.getEmail())
			.name(user.getName())
			.nickname(user.getNickname())
			.birth(user.getBirth())
			.build();
	}
}
