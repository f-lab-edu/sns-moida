package com.moida.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.moida.domain.User;
import com.moida.exception.DuplicatedEmailException;
import com.moida.exception.DuplicatedIdException;
import com.moida.mapper.UserMapper;
import com.moida.repository.user.UserRepository;
import com.moida.request.SingUpRequest;
import com.moida.request.UpdateUserRequest;
import com.moida.response.SignUpResponse;
import com.moida.response.UpdateUserResponse;

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
	public SignUpResponse create(SingUpRequest request) {
		User user = request.toUser();

		if (userRepository.findByUserId(request.getUserId()) != null) {
			throw new DuplicatedIdException();
		}

		if (userRepository.findByUserEmail(request.getEmail()) != null) {
			throw new DuplicatedEmailException();
		}

		userRepository.insert(user);

		User savedUser = userRepository.findByUserId(user.getUserId());
		return savedUser.toSingUpResponse();
	}

	@Transactional
	public UpdateUserResponse modifyUser(String userId, UpdateUserRequest request) {
		User user = request.toUser();

		userRepository.update(user);

		User updateUser = userRepository.findByUserId(userId);
		return updateUser.toUpdateResponse();
	}

	public User getUserById(String userId) {
		return userRepository.findByUserId(userId);
	}

	public List<User> getUserList() {
		return userRepository.findUserList();
	}

	public void remove(String userId) {
		userRepository.delete(userId);
	}
}
