package com.moida.mapper;

import java.util.List;
import java.util.Optional;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.moida.domain.User;

@Mapper
public interface UserMapper {

	void insert(User user);

	void update(@Param("userId") String userId, @Param("user") User user);

	User findByUserId(String userId);

	User findByUserEmail(String email);

	void delete(String userId);

	List<User> findUserList();

	Optional<User> findByUserIdAndPassword(@Param("userId") String userId, @Param("password") String password);
}
