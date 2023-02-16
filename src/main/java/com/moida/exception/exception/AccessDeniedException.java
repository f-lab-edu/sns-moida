package com.moida.exception.exception;

import org.springframework.http.HttpStatus;

import com.moida.exception.BaseException;

import lombok.Getter;

@Getter
public class AccessDeniedException extends BaseException {

	public AccessDeniedException() {
	}

	public AccessDeniedException(String message) {
		super(message);
	}

	public AccessDeniedException(String message, Throwable cause) {
		super(message, cause);
	}

	public AccessDeniedException(Throwable cause) {
		super(cause);
	}

	@Override
	public HttpStatus getStatus() {
		return HttpStatus.FORBIDDEN;
	}

	@Override
	public String getMessage() {
		return "접근이 허용되지 않습니다.";
	}
}
