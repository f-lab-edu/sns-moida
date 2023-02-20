package com.moida.exception;

import org.springframework.http.HttpStatus;

import com.moida.exception.base.BaseException;

import lombok.Getter;

@Getter
public class DuplicatedIdException extends BaseException {
	public DuplicatedIdException() {
	}

	public DuplicatedIdException(String message) {
		super(message);
	}

	public DuplicatedIdException(String message, Throwable cause) {
		super(message, cause);
	}

	public DuplicatedIdException(Throwable cause) {
		super(cause);
	}

	@Override
	public HttpStatus getStatus() {
		return HttpStatus.BAD_REQUEST;
	}

	@Override
	public String getMessage() {
		return "중복된 아이디 입니다.";
	}
}
