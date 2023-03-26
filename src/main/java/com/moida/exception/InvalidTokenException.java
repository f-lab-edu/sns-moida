package com.moida.exception;

import org.springframework.http.HttpStatus;

import com.moida.exception.base.BaseException;

public class InvalidTokenException extends BaseException {

	public InvalidTokenException() {
	}

	public InvalidTokenException(String message) {
		super(message);
	}

	public InvalidTokenException(Throwable cause) {
		super(cause);
	}

	public InvalidTokenException(String message, Throwable cause) {
		super(message, cause);
	}

	@Override
	public HttpStatus getStatus() {
		return HttpStatus.UNAUTHORIZED;
	}

	@Override
	public String getMessage() {
		return "유효하지 않는 토큰입니다.";
	}
}
