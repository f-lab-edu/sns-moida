package com.moida.exception.exception;

import org.springframework.http.HttpStatus;

import com.moida.exception.BaseException;

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
		return "로그인에 실패했습니다.";
	}
}
