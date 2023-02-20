package com.moida.domain;

import java.sql.Date;
import java.time.LocalDateTime;

import com.moida.response.SignUpResponse;
import com.moida.response.UpdateUserResponse;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class User {
	private int id;

	private String userId;

	private String password;

	private String email;

	private String name;

	private String nickname;

	private Date birth;

	private String profileImagePath;

	private LocalDateTime createdAt;

	private LocalDateTime updatedAt;

	@Builder
	public User(int id, String userId, String password, String email, String name, String nickname, Date birth,
		String profileImagePath, LocalDateTime createdAt, LocalDateTime updatedAt) {
		this.id = id;
		this.userId = userId;
		this.password = password;
		this.email = email;
		this.name = name;
		this.nickname = nickname;
		this.birth = birth;
		this.profileImagePath = profileImagePath;
		this.createdAt = createdAt;
		this.updatedAt = updatedAt;
	}

	public SignUpResponse toSingUpResponse() {
		return SignUpResponse.builder()
			.userId(userId)
			.password(password)
			.email(email)
			.name(name)
			.nickname(nickname)
			.birth(birth)
			.build();
	}

	public UpdateUserResponse toUpdateResponse() {
		return UpdateUserResponse.builder()
			.name(name)
			.nickname(nickname)
			.birth(birth)
			.profileImagePath(profileImagePath)
			.build();
	}
}
