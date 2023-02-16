package com.moida.exception;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice(basePackages = "com.moida.controller")
public class GlobalExceptionHandler {

	/*
	애플리케이션으로 만든 예외 처리
	 */

	@ExceptionHandler(BaseException.class)
	public ResponseEntity<ErrorResponse> handleNotFoundException(BaseException ex, HttpServletRequest request) {
		return createErrorResponse(ex, request);
	}

	/*
	예상치 못한 예외 처리
	 */
	@ExceptionHandler(Exception.class)
	public ResponseEntity<String> handleException(Exception ex) {
		return new ResponseEntity<>(ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
	}

	public ResponseEntity<ErrorResponse> createErrorResponse(
		BaseException ex, HttpServletRequest request) {
		return ResponseEntity.status(ex.getStatus())
			.body(
				ErrorResponse.builder()
					.status(ex.getStatus().value())
					.error(ex.getStatus().name())
					.message(ex.getMessage())
					.path(request.getRequestURI())
					.build());
	}
}
