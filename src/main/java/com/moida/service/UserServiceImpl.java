package com.moida.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.moida.dto.UserDto;
import com.moida.mapper.UserMapper;
import com.moida.model.user.UserSingUp;
import com.moida.repository.user.UserRepository;

@Service
public class UserServiceImpl implements UserService {

	private final UserRepository userRepository;

	//테스트 코드
	private final UserMapper userMapper;

	public UserServiceImpl(UserRepository userRepository, UserMapper userMapper) {
		this.userRepository = userRepository;
		this.userMapper = userMapper;
	}

	@Transactional
	@Override
	public void signUpUser(UserSingUp userSingUp) {
		userRepository.insertUser(userSingUp);
	}

	@Override
	public boolean duplicationUserId(String user_id) {
		return userRepository.duplicationUserId(user_id);
	}

	@Override
	public boolean duplicationEmail(String email) {
		return userRepository.duplicationEmail(email);
	}

	@Override
	public List<UserDto> getUserDtoList() {
		return userRepository.getUserDtoList();
	}
}
