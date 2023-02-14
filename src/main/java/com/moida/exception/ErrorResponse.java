package com.moida.exception;

import java.time.LocalDateTime;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class ErrorResponse {

	private int code;
	private String message;
	private LocalDateTime timeStamp;

	public ErrorResponse(ErrorCode errorCode) {
		this.code = errorCode.getStatus();
		this.message = errorCode.getMessage();
		this.timeStamp = errorCode.getTimeStamp();
	}
}
