package com.moida.model.user;

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
public class UserSingUp {

	@NotBlank
	String user_id;

	@NotBlank
	String password;

	@NotBlank
	String name;

	@NotBlank
	@Email
	String email;

	@NotBlank
	String nickname;

	Date birth;

	String profile_image_path;

	LocalDateTime created_at;

	LocalDateTime updated_at;
}
