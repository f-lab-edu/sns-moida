package com.moida.request;

import java.sql.Date;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

import com.moida.domain.User;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

/*
user 회원가입

*/
@Getter
@NoArgsConstructor
public class SingUpRequest {

	@NotBlank(message = "아이디를 입력해주세요")
	String userId;

	@NotBlank(message = "비밀번호를 입력해주세요")
	String password;

	@NotBlank(message = "이메일을 입력해주세요")
	@Email
	String email;

	@NotBlank(message = "이름을 입력해주세요")
	String name;

	@NotBlank(message = "닉네임을 입력해주세요")
	String nickname;

	Date birth;

	@Builder
	public SingUpRequest(String userId, String password, String name, String email, String nickname, Date birth) {
		this.userId = userId;
		this.password = password;
		this.email = email;
		this.name = name;
		this.nickname = nickname;
		this.birth = birth;
	}

	public User toUser() {
		return User.builder()
			.userId(userId)
			.password(password)
			.email(email)
			.name(name)
			.nickname(nickname)
			.birth(birth)
			.build();
	}
}
