package com.moida.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.moida.dto.UserDto;
import com.moida.model.user.UserLogin;
import com.moida.model.user.UserSingUp;

@Mapper
public interface UserMapper {

	// 회원가입
	void insertUser(UserSingUp userSingUp);

	// 아이디 중복 검사
	boolean duplicationUserId(String userId);

	// 이메일 중복 검사
	boolean duplicationEmail(String email);

	// 로그인
	void login(UserLogin userLogin);

	List<UserDto> getUserDtoList();
}
