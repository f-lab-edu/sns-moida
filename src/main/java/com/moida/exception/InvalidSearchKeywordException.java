package com.moida.exception;

import org.springframework.http.HttpStatus;

import com.moida.exception.base.BaseException;

import lombok.Getter;

@Getter
public class InvalidSearchKeywordException extends BaseException {

	public InvalidSearchKeywordException() {
	}

	public InvalidSearchKeywordException(String message) {
		super(message);
	}

	public InvalidSearchKeywordException(String message, Throwable cause) {
		super(message, cause);
	}

	public InvalidSearchKeywordException(Throwable cause) {
		super(cause);
	}

	@Override
	public HttpStatus getStatus() {
		return HttpStatus.BAD_REQUEST;
	}

	@Override
	public String getMessage() {
		return "검색어를 입력해주세요.";
	}
}
