package com.moida.response;

import java.sql.Date;

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
}
