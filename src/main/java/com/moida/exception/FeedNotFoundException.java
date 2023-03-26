package com.moida.exception;

import org.springframework.http.HttpStatus;

import com.moida.exception.base.BaseException;

import lombok.Getter;

@Getter
public class FeedNotFoundException extends BaseException {

	public FeedNotFoundException() {
	}

	public FeedNotFoundException(String message) {
		super(message);
	}

	public FeedNotFoundException(Throwable cause) {
		super(cause);
	}

	public FeedNotFoundException(String message, Throwable cause) {
		super(message, cause);
	}

	@Override
	public HttpStatus getStatus() {
		return HttpStatus.NOT_FOUND;
	}

	@Override
	public String getMessage() {
		return "게시물을 찾을 수 없습니다.";
	}
}
