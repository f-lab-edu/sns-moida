package com.moida.controller;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.moida.dto.ErrorResponse;
import com.moida.exception.AccessDeniedException;
import com.moida.exception.LoginFailException;
import com.moida.exception.UserNotFoundException;

@RestControllerAdvice(basePackages = "com.moida.controller")
public class ControllerErrorAdvice {

	@ExceptionHandler(UserNotFoundException.class)
	public ResponseEntity<ErrorResponse> handleUserNotFound(UserNotFoundException e) {
		ErrorResponse response = new ErrorResponse(HttpStatus.NOT_FOUND, e.getMessage(), LocalDateTime.now());
		return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler(LoginFailException.class)
	public ResponseEntity<ErrorResponse> handleLoginFile(LoginFailException e) {
		ErrorResponse response = new ErrorResponse(HttpStatus.BAD_REQUEST, e.getMessage(), LocalDateTime.now());
		return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(AccessDeniedException.class)
	public ResponseEntity<ErrorResponse> handleAccessDenied(AccessDeniedException e) {
		ErrorResponse response = new ErrorResponse(HttpStatus.FORBIDDEN, e.getMessage(), LocalDateTime.now());
		return new ResponseEntity<>(response, HttpStatus.FORBIDDEN);
	}
}
