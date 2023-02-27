package com.moida.response;

import java.sql.Date;
import java.time.LocalDateTime;

import com.moida.domain.User;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class UserResponse {

	@Schema(description = "사용자 아이디", example = "AFK150")
	private String userId;

	@Schema(description = "비밀번호", example = "1234asdf")
	private String password;

	@Schema(description = "이메일", example = "xodnzlzl1597@gmail.com")
	private String email;

	@Schema(description = "회원 이름", example = "김태우")
	private String name;

	@Schema(description = "닉네임", example = "ririti")
	private String nickname;

	@Schema(description = "생년월일", example = "yyyy-mm-dd")
	private Date birth;

	@Schema(description = "파일저장 위치", example = "C:\\Users\\Documents\\Black Desert")
	private String profileImagePath;

	@Schema(description = "생성 일자", example = "1996-05-13")
	private LocalDateTime createdAt;

	@Schema(description = "수정 일자", example = "2023-02-23")
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
