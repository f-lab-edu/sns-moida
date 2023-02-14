package com.moida.exception.exception;

import com.moida.exception.ErrorCode;

import lombok.Getter;

@Getter
public class LoginFailException extends RuntimeException {

	private final ErrorCode errorCode;

	public LoginFailException(ErrorCode errorCode) {
		this.errorCode = errorCode;
	}
}
