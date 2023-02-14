package com.moida.exception.exception;

import com.moida.exception.ErrorCode;

import lombok.Getter;

@Getter
public class UserNotFoundException extends RuntimeException {

	private final ErrorCode errorCode;

	public UserNotFoundException(ErrorCode errorCode) {
		this.errorCode = errorCode;
	}
}
