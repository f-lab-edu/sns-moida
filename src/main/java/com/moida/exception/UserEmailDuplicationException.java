package com.moida.exception;

public class UserEmailDuplicationException extends RuntimeException {
	public UserEmailDuplicationException(String email) {
		super("이미 사용중인 이메일 입니다. : " + email);
	}
}
