package com.moida.exception;

import org.springframework.http.HttpStatus;

import com.moida.exception.base.BaseException;

public class CommentNotFoundException extends BaseException {
	public CommentNotFoundException(Throwable cause) {
		super(cause);
	}

	public CommentNotFoundException() {
		super();
	}

	public CommentNotFoundException(String message) {
		super(message);
	}

	public CommentNotFoundException(String message, Throwable cause) {
		super(message, cause);
	}

	@Override
	public HttpStatus getStatus() {
		return HttpStatus.NOT_FOUND;
	}

	@Override
	public String getMessage() {
		return "댓글을 찾을 수 없습니다.";
	}
}
