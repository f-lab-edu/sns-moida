package com.moida.exception;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ErrorCode {

	ACCESS_DENIED(403, "잘못된 접근입니다.", LocalDateTime.now()),

	USER_NOT_EXIST(404, "해당 유저는 존재하지 않습니다.", LocalDateTime.now()),
	LOGIN_FAIL(404, "로그인을 실패 했습니다.", LocalDateTime.now()),

	ID_DUPLICATED(409, "이미 존재하는 아이디입니다.", LocalDateTime.now()),
	EMAIL_DUPLICATED(409, "이미 존재하는 이메일입니다.", LocalDateTime.now());

	private int status;
	private String message;
	private LocalDateTime timeStamp;
}
