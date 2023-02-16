package com.moida.exception;

import org.springframework.http.HttpStatus;

public abstract class BaseException extends RuntimeException {

	public BaseException() {
	}

	public BaseException(String message) {
		super(message);
	}

	public BaseException(Throwable cause) {
		super(cause);
	}

	public BaseException(String message, Throwable cause) {
		super(message, cause);
	}

	public abstract HttpStatus getStatus();

	public abstract String getMessage();

}
