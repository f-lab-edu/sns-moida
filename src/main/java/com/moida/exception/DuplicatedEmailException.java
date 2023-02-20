package com.moida.exception;

import org.springframework.http.HttpStatus;

import com.moida.exception.base.BaseException;

import lombok.Getter;

@Getter
public class DuplicatedEmailException extends BaseException {
	public DuplicatedEmailException() {
	}

	public DuplicatedEmailException(String message) {
		super(message);
	}

	public DuplicatedEmailException(String message, Throwable cause) {
		super(message, cause);
	}

	public DuplicatedEmailException(Throwable cause) {
		super(cause);
	}

	@Override
	public HttpStatus getStatus() {
		return HttpStatus.BAD_REQUEST;
	}

	@Override
	public String getMessage() {
		return "중복된 이메일 입니다.";
	}
}
