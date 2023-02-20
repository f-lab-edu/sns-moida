package com.moida.request;

import java.sql.Date;

import javax.validation.constraints.NotBlank;

import com.moida.domain.User;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class UpdateUserRequest {

	@NotBlank(message = "이름을 입력해주세요")
	String name;

	@NotBlank(message = "닉네임을 입력해주세요")
	String nickname;

	Date birth;

	String profileImagePath;

	@Builder
	public UpdateUserRequest(String name, String nickname, Date birth, String profileImagePath) {
		this.name = name;
		this.nickname = nickname;
		this.birth = birth;
		this.profileImagePath = profileImagePath;
	}

	public User toUser() {
		return User.builder()
			.name(name)
			.nickname(nickname)
			.birth(birth)
			.profileImagePath(profileImagePath)
			.build();
	}
}
