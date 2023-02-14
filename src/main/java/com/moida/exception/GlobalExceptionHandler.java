package com.moida.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.moida.exception.exception.AccessDeniedException;
import com.moida.exception.exception.DuplicatedException;
import com.moida.exception.exception.LoginFailException;
import com.moida.exception.exception.UserNotFoundException;

@RestControllerAdvice(basePackages = "com.moida.controller")
public class GlobalExceptionHandler {

	@ExceptionHandler(UserNotFoundException.class)
	public ResponseEntity<ErrorResponse> handleUserNotFound(UserNotFoundException e) {
		return new ResponseEntity<>(new ErrorResponse(e.getErrorCode()),
			HttpStatus.valueOf(e.getErrorCode().getStatus()));
	}

	@ExceptionHandler(LoginFailException.class)
	public ResponseEntity<ErrorResponse> handleLoginFile(LoginFailException e) {
		return new ResponseEntity<>(new ErrorResponse(e.getErrorCode()),
			HttpStatus.valueOf(e.getErrorCode().getStatus()));
	}

	@ExceptionHandler(AccessDeniedException.class)
	public ResponseEntity<ErrorResponse> handleAccessDenied(AccessDeniedException e) {
		return new ResponseEntity<>(new ErrorResponse(e.getErrorCode()),
			HttpStatus.valueOf(e.getErrorCode().getStatus()));
	}

	@ExceptionHandler(DuplicatedException.class)
	public ResponseEntity<ErrorResponse> handleUserEmailDuplication(DuplicatedException e) {
		return new ResponseEntity<>(new ErrorResponse(e.getErrorCode()),
			HttpStatus.valueOf(e.getErrorCode().getStatus()));
	}
}
