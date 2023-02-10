package com.moida.exception;

public class LoginFailException extends RuntimeException {
	public LoginFailException() {
		super("로그인과 비밀번호를 다시 확인해주세요 ");
	}
}
