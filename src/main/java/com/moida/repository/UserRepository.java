package com.moida.repository;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.moida.domain.User;
import com.moida.mapper.UserMapper;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class UserRepository {

	private final UserMapper userMapper;

	public void insert(User user) {
		userMapper.insert(user);
	}

	public void update(String userId, User user) {
		userMapper.update(userId, user);
	}

	public User findByUserId(String userId) {
		return userMapper.findByUserId(userId);
	}

	public User findByUserEmail(String email) {
		return userMapper.findByUserEmail(email);
	}

	public void delete(String userId) {
		userMapper.delete(userId);
	}

	public List<User> findUserList() {
		return userMapper.findUserList();
	}
}
