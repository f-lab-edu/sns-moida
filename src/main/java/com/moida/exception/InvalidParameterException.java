package com.moida.exception;

import org.springframework.http.HttpStatus;

import com.moida.exception.base.BaseException;

import lombok.Getter;

@Getter
public class InvalidParameterException extends BaseException {
	public InvalidParameterException() {
		super();
	}

	public InvalidParameterException(String message) {
		super(message);
	}

	public InvalidParameterException(Throwable cause) {
		super(cause);
	}

	public InvalidParameterException(String message, Throwable cause) {
		super(message, cause);
	}

	@Override
	public HttpStatus getStatus() {
		return HttpStatus.BAD_REQUEST;
	}

	@Override
	public String getMessage() {
		return "입력하신 정보를 다시 확인해주세요.";
	}
}
