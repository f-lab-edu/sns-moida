package com.moida.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.moida.dto.TestDto;
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

	@GetMapping("/user")
	public List<TestDto> user() {
		return testService.getUserList();
	}
}
