package com.moida.exception.exception;

import com.moida.exception.ErrorCode;

import lombok.Getter;

@Getter
public class AccessDeniedException extends RuntimeException {

	private ErrorCode errorCode;

	public AccessDeniedException(ErrorCode errorCode) {
		this.errorCode = errorCode;
	}
}
