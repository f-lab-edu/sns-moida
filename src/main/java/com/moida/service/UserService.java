package com.moida.service;

import java.util.List;

import com.moida.dto.UserDto;
import com.moida.model.user.UserSingUp;

public interface UserService {

	//회원가입
	void signUpUser(UserSingUp userSingUp);

	// 중복된 유저아이디
	boolean duplicationUserId(String userId);

	// 중복된 이메일
	boolean duplicationEmail(String email);

	//유저리스트 가져오는 테스트
	List<UserDto> getUserDtoList();
}
