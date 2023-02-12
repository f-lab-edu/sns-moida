package com.moida.dto;

import java.sql.Date;
import java.time.LocalDateTime;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
public class UserDto {

	private int id;

	private String user_id;

	private String password;

	private String email;

	private String name;

	private String nickname;

	private Date birth;

	private String profile_image_path;

	private LocalDateTime created_at;

	private LocalDateTime updated_at;

	@Builder
	public UserDto(int id, String user_id, String password, String email, String name, String nickname, Date birth,
		String profile_image_path, LocalDateTime created_at, LocalDateTime updated_at) {
		this.id = id;
		this.user_id = user_id;
		this.password = password;
		this.email = email;
		this.name = name;
		this.nickname = nickname;
		this.birth = birth;
		this.profile_image_path = profile_image_path;
		this.created_at = created_at;
		this.updated_at = updated_at;
	}
}
