package com.moida.exception;

import org.springframework.http.HttpStatus;

import com.moida.exception.base.BaseException;

public class LoginRequiredException extends BaseException {

	public LoginRequiredException() {
	}

	public LoginRequiredException(String message) {
		super(message);
	}

	public LoginRequiredException(Throwable cause) {
		super(cause);
	}

	public LoginRequiredException(String message, Throwable cause) {
		super(message, cause);
	}

	@Override
	public HttpStatus getStatus() {
		return HttpStatus.UNAUTHORIZED;
	}

	@Override
	public String getMessage() {
		return "로그인이 필요합니다.";
	}
}
