package com.moida.exception;

import org.springframework.http.HttpStatus;

import com.moida.exception.base.BaseException;

import lombok.Getter;

@Getter
public class LoginFailException extends BaseException {

	public LoginFailException() {
	}

	public LoginFailException(String message) {
		super(message);
	}

	public LoginFailException(String message, Throwable cause) {
		super(message, cause);
	}

	public LoginFailException(Throwable cause) {
		super(cause);
	}

	@Override
	public HttpStatus getStatus() {
		return HttpStatus.BAD_REQUEST;
	}

	@Override
	public String getMessage() {
		return "아이디와 비밀번호를 다시한번 확인해주세요.";
	}
}
