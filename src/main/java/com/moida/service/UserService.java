package com.moida.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.moida.dto.UserDto;
import com.moida.mapper.UserMapper;
import com.moida.model.user.UserSingUp;
import com.moida.repository.user.UserRepository;

@Service
public class UserService {

	//회원가입
	private final UserRepository userRepository;

	//테스트 코드
	private final UserMapper userMapper;

	public UserService(UserRepository userRepository, UserMapper userMapper) {
		this.userRepository = userRepository;
		this.userMapper = userMapper;
	}

	@Transactional
	public void signUpUser(UserSingUp userSingUp) {
		userRepository.insertUser(userSingUp);
	}

	public boolean duplicationUserId(String userId) {
		return userRepository.duplicationUserId(userId);
	}

	public boolean duplicationEmail(String email) {
		return userRepository.duplicationEmail(email);
	}

	public List<UserDto> getUserDtoList() {
		return userRepository.getUserDtoList();
	}
}
