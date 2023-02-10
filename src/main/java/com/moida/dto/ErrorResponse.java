package com.moida.dto;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class ErrorResponse {

	private HttpStatus code;
	private String message;
	private LocalDateTime timeStamp;

	public ErrorResponse(HttpStatus code, String message, LocalDateTime timeStamp) {
		this.code = code;
		this.message = message;
		this.timeStamp = timeStamp;
	}
}
