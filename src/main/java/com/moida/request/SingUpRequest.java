package com.moida.request;

import java.sql.Date;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

import com.moida.domain.User;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

/*
user 회원가입

*/
@Getter
@NoArgsConstructor
public class SingUpRequest {

	@Schema(description = "사용자 아이디", example = "AFK150")
	@NotBlank(message = "아이디를 입력해주세요")
	String userId;

	@Schema(description = "비밀번호", example = "1234asdf")
	@NotBlank(message = "비밀번호를 입력해주세요")
	String password;

	@Schema(description = "이메일", example = "xodnzlzl1597@gmail.com")
	@NotBlank(message = "이메일을 입력해주세요")
	@Email
	String email;

	@Schema(description = "회원 이름", example = "김태우")
	@NotBlank(message = "이름을 입력해주세요")
	String name;

	@Schema(description = "닉네임", example = "ririti")
	@NotBlank(message = "닉네임을 입력해주세요")
	String nickname;

	@Schema(description = "생년월일", example = "yyyy-mm-dd")
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
