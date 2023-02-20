package com.moida.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.moida.domain.User;

@Mapper
public interface UserMapper {

	void insert(User user);

	void update(User user);

	User findByUserId(String userId);

	User findByUserEmail(String email);

	void delete(String userId);

	List<User> findUserList();
}
