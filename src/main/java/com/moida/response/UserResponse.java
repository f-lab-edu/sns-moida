package com.moida.response;

import java.sql.Date;
import java.time.LocalDateTime;

import com.moida.domain.User;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class UserResponse {

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
	public UserResponse(String userId, String password, String email, String name, String nickname, Date birth,
		String profileImagePath, LocalDateTime createdAt, LocalDateTime updatedAt) {
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

	public static UserResponse from(User user) {
		return UserResponse.builder()
			.userId(user.getUserId())
			.password(user.getPassword())
			.email(user.getEmail())
			.name(user.getName())
			.nickname(user.getNickname())
			.birth(user.getBirth())
			.profileImagePath(user.getProfileImagePath())
			.createdAt(user.getCreatedAt())
			.updatedAt(user.getUpdatedAt())
			.build();
	}
}
