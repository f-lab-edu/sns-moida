package com.moida.exception.exception;

import com.moida.exception.ErrorCode;

import lombok.Getter;

@Getter
public class DuplicatedException extends RuntimeException {

	private ErrorCode errorCode;

	private DuplicatedException(ErrorCode errorCode) {
		this.errorCode = errorCode;
	}
}
