package com.moida.exception;

public class UserIdDuplicationException extends RuntimeException {
	public UserIdDuplicationException(String user_id) {
		super("사용중인 아이디 입니다. : " + user_id);
	}
}
