package com.moida.exception.handler;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.moida.exception.base.BaseException;
import com.moida.response.ErrorResponse;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestControllerAdvice(basePackages = "com.moida.controller")
public class GlobalExceptionHandler {

	@ExceptionHandler(BaseException.class)
	public ResponseEntity<ErrorResponse> handleNotFoundException(BaseException ex, HttpServletRequest request) {
		return createErrorResponse(ex, request);
	}

	@ExceptionHandler(Exception.class)
	public ResponseEntity<String> handleException(Exception ex) {
		log.error("");
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
