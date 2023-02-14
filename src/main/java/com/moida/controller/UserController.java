package com.moida.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.moida.dto.UserDto;
import com.moida.model.user.UserSingUp;
import com.moida.service.UserService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/user")
public class UserController {

	private final UserService userService;

	// 회원가입
	@PostMapping("/signup")
	@ResponseStatus(HttpStatus.CREATED)
	public HttpStatus userSingUp(@RequestBody @Valid UserSingUp userSingUp) {
		userService.signUpUser(userSingUp);
		return HttpStatus.CREATED;
	}

	// 중복된 아이디
	@GetMapping("/{userId}/duplicateId")
	@ResponseStatus(HttpStatus.CONFLICT)
	public HttpStatus duplicationUserId(@PathVariable String userId) {
		userService.duplicationUserId(userId);
		return HttpStatus.CONFLICT;
	}

	// 중복된 이메일
	@GetMapping("/{email}/duplicateEmail")
	@ResponseStatus(HttpStatus.CONFLICT)
	public HttpStatus duplicationEmail(@PathVariable String email) {
		userService.duplicationEmail(email);
		return HttpStatus.CONFLICT;
	}

	// 유저 리스트 반환
	@GetMapping
	public List<UserDto> user() {
		return userService.getUserDtoList();
	}
}
