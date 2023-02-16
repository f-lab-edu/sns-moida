package com.moida.model.user.request;

import java.sql.Date;
import java.time.LocalDateTime;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

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

	@NotBlank(message = "이름을 입력해주세요")
	String name;

	@NotBlank(message = "이메일을 입력해주세요")
	@Email
	String email;

	@NotBlank(message = "닉네임을 입력해주세요")
	String nickname;

	Date birth;

	String profileImagePath;

	LocalDateTime createdAt;

	LocalDateTime updatedAt;
}
