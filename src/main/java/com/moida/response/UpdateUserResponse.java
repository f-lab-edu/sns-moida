package com.moida.response;

import java.sql.Date;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class UpdateUserResponse {

	String name;

	String nickname;

	Date birth;

	String profileImagePath;

	@Builder
	public UpdateUserResponse(String name, String nickname, Date birth, String profileImagePath) {
		this.name = name;
		this.nickname = nickname;
		this.birth = birth;
		this.profileImagePath = profileImagePath;
	}
}
