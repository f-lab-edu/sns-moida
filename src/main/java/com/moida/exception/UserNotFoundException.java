package com.moida.exception;

public class UserNotFoundException extends RuntimeException {
	public UserNotFoundException(String userId) {
		super("사용자를 찾을 수 없습니다. " + userId);
	}
}
