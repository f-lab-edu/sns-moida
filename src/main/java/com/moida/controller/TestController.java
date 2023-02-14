package com.moida.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.moida.dto.TestDto;
import com.moida.exception.ErrorCode;
import com.moida.exception.exception.AccessDeniedException;
import com.moida.exception.exception.LoginFailException;
import com.moida.exception.exception.UserNotFoundException;
import com.moida.service.TestService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class TestController {
	private final TestService testService;

	@GetMapping
	public String test() {
		return "Hello World!";
	}

	// @ExceptionHandle 예제
	@GetMapping("/api2/members/{id}")
	public TestDto getTestDto(@PathVariable("id") String id) {

		if (id.equals("forbidden")) {
			throw new AccessDeniedException(ErrorCode.ACCESS_DENIED);
		}
		if (id.equals("bad")) {
			throw new LoginFailException(ErrorCode.LOGIN_FAIL);
		}
		if (id.equals("not")) {
			throw new UserNotFoundException(ErrorCode.USER_NOT_EXIST);
		}
		return new TestDto();
	}

	@GetMapping("/userss")
	public List<TestDto> user() {
		return testService.getUserList();
	}
}
