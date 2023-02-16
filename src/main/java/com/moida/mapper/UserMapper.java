package com.moida.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.moida.dto.UserDto;
import com.moida.model.user.request.LoginRequest;
import com.moida.model.user.request.SingUpRequest;

@Mapper
public interface UserMapper {

	// 회원가입
	void insertUser(SingUpRequest singUpRequest);

	// 아이디 중복 검사
	boolean findByUserId(String userId);

	// 이메일 중복 검사
	boolean findByUserEmail(String email);

	// 로그인
	void login(LoginRequest loginRequest);

	List<UserDto> getUserDtoList();
}
