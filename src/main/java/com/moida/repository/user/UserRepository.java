package com.moida.repository.user;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.moida.dto.UserDto;
import com.moida.mapper.UserMapper;
import com.moida.model.user.request.SingUpRequest;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class UserRepository {

	private final UserMapper userMapper;

	public void insertUser(SingUpRequest userSingUp) {
		userMapper.insertUser(userSingUp);
	}

	public boolean findByUserId(String userId) {
		return userMapper.findByUserId(userId);
	}

	public boolean findByUserEmail(String email) {
		return userMapper.findByUserEmail(email);
	}

	public List<UserDto> getUserDtoList() {
		return userMapper.getUserDtoList();
	}
}
