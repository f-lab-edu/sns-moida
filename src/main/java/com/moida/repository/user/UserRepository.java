package com.moida.repository.user;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.moida.dto.UserDto;
import com.moida.mapper.UserMapper;
import com.moida.model.user.UserSingUp;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class UserRepository {

	private final UserMapper userMapper;

	public void insertUser(UserSingUp userSingUp) {
		userMapper.insertUser(userSingUp);
	}

	public boolean duplicationUserId(String userId) {
		return userMapper.duplicationUserId(userId);
	}

	public boolean duplicationEmail(String email) {
		return userMapper.duplicationEmail(email);
	}

	public List<UserDto> getUserDtoList() {
		return userMapper.getUserDtoList();
	}
}
