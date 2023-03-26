package com.moida.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.moida.config.PasswordEncoder;
import com.moida.domain.User;
import com.moida.exception.DuplicatedEmailException;
import com.moida.exception.DuplicatedIdException;
import com.moida.exception.InvalidParameterException;
import com.moida.exception.InvalidSearchKeywordException;
import com.moida.exception.UserNotFoundException;
import com.moida.repository.UserRepository;
import com.moida.request.CreateUserRequest;
import com.moida.request.UpdateUserRequest;
import com.moida.response.UserResponse;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserService {

	private final UserRepository userRepository;
	private final PasswordEncoder passwordEncoder;

	@Transactional
	public UserResponse create(CreateUserRequest request) {
		String encryptedPassword = passwordEncoder.encrypt(request.getPassword());

		User user = request.toUser(encryptedPassword);

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
		User existingUser = userRepository.findByUserId(userId);
		if (existingUser == null) {
			throw new UserNotFoundException();
		}

		User user = request.toUser(existingUser);
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

	@Transactional
	public void remove(String userId) {
		User existingUser = userRepository.findByUserId(userId);
		if (existingUser == null) {
			throw new UserNotFoundException();
		}

		userRepository.delete(userId);
	}

	public List<User> searchByKeyword(String keyword) {
		if (keyword == null || keyword.trim().isEmpty()) {
			throw new InvalidSearchKeywordException();
		}
		return userRepository.searchByKeyword(keyword.trim());
	}

	public List<User> searchByName(String name) {
		if (name == null || name.trim().isEmpty()) {
			throw new InvalidSearchKeywordException();
		}
		return userRepository.searchByName(name.trim());
	}

	public List<User> searchByNickname(String nickname) {
		if (nickname == null || nickname.trim().isEmpty()) {
			throw new InvalidSearchKeywordException();
		}
		return userRepository.searchByNickname(nickname.trim());
	}

	public List<User> getRecommendedUsers(int limit) {
		validateLimit(limit);
		return userRepository.findRecentUsers(limit);
	}

	private void validateLimit(int limit) {
		if (limit <= 0 || limit > 100) {
			throw new InvalidParameterException();
		}
	}
}
