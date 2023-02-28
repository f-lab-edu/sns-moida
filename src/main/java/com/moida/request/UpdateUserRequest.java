package com.moida.request;

import java.sql.Date;

import javax.validation.constraints.NotBlank;

import com.moida.domain.User;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class UpdateUserRequest {

	@Schema(description = "회원 이름", example = "김재우")
	@NotBlank(message = "이름을 입력해주세요")
	String name;

	@Schema(description = "닉네임", example = "sim")
	@NotBlank(message = "닉네임을 입력해주세요")
	String nickname;

	@Schema(description = "생년월일", example = "1111-22-33")
	Date birth;

	@Schema(description = "파일저장 위치", example = "C:\\Users\\Documents\\LOL")
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
