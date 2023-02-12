package com.moida.exception;

public class UserNotFoundException extends RuntimeException {
	public UserNotFoundException(String user_id) {
		super("사용자를 찾을 수 없습니다. " + user_id);
	}
}
