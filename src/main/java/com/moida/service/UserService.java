package com.moida.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.moida.domain.User;
import com.moida.exception.DuplicatedEmailException;
import com.moida.exception.DuplicatedIdException;
import com.moida.repository.UserRepository;
import com.moida.request.SingUpRequest;
import com.moida.request.UpdateUserRequest;
import com.moida.response.UserResponse;

@Service
public class UserService {

	private final UserRepository userRepository;

	public UserService(UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	@Transactional
	public UserResponse create(SingUpRequest request) {
		User user = request.toUser();

		if (userRepository.findByUserId(request.getUserId()) != null) {
			throw new DuplicatedIdException();
		}

		if (userRepository.findByUserEmail(request.getEmail()) != null) {
			throw new DuplicatedEmailException();
		}

		userRepository.insert(user);

		User savedUser = userRepository.findByUserId(user.getUserId());
		return UserResponse.from(savedUser);
	}

	@Transactional
	public UserResponse modifyUser(String userId, UpdateUserRequest request) {
		User user = request.toUser();

		userRepository.update(userId, user);

		User updateUser = userRepository.findByUserId(userId);
		return UserResponse.from(updateUser);
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
