package com.moida.response;

import java.sql.Date;

import com.moida.domain.User;

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

	public static UpdateUserResponse from(User user) {
		return UpdateUserResponse.builder()
			.name(user.getName())
			.nickname(user.getNickname())
			.birth(user.getBirth())
			.profileImagePath(user.getProfileImagePath())
			.build();
	}
}
