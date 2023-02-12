package com.moida.dto;

import static org.assertj.core.api.Assertions.*;

import java.sql.Date;

import org.junit.jupiter.api.Test;

class UserDtoTest {

	@Test
	void createUser() {

		UserDto userData = UserDto.builder()
			.id(1)
			.user_id("첫번쨰")
			.password("149asdf")
			.email("xodnzlzl1597@gmail.com")
			.name("김태우")
			.nickname("ririti")
			.birth(Date.valueOf("1996-05-13"))
			.profile_image_path("C:\\Users\\Downloads\\UI")
			//.create_at()
			//.update_at()
			.build();

		assertThat(userData.getId()).isEqualTo(1);
		assertThat(userData.getUser_id()).isEqualTo("첫번쨰");
		assertThat(userData.getPassword()).isEqualTo("149asdf");
		assertThat(userData.getEmail()).isEqualTo("xodnzlzl1597@gmail.com");
		assertThat(userData.getName()).isEqualTo("김태우");
		assertThat(userData.getNickname()).isEqualTo("ririti");
		assertThat(userData.getBirth()).isEqualTo("1996-05-13");
		assertThat(userData.getProfile_image_path()).isEqualTo("C:\\Users\\Downloads\\UI");
	}

}
