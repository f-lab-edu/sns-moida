package com.moida.exception;

public class AccessDeniedException extends RuntimeException {
	public AccessDeniedException() {
		super("잘못된 접근입니다");
	}
}
