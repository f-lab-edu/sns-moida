package com.moida.controller;

import static com.moida.util.HttpResponse.*;

import java.util.List;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.moida.dto.UserDto;
import com.moida.model.user.request.SingUpRequest;
import com.moida.service.UserService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
public class UserController {

	private final UserService userService;

	// 회원가입
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public ResponseEntity<Void> userSingUp(@RequestBody @Valid SingUpRequest singUpRequest) {
		userService.signUpUser(singUpRequest);
		return CREATE;
	}

	// 로그인
	// @PostMapping("/login")
	// public ResponseEntity<Void> user(@RequestBody @Valid LoginRequest loginRequest) {
	// 	userService.loginUser(loginRequest);
	// 	return OK;
	// }

	// 중복된 아이디
	@GetMapping("/{userId}")
	public ResponseEntity<Void> duplicationUserId(@PathVariable String userId) {
		boolean duplicated = userService.duplicationUserId(userId);

		if (duplicated) {
			return CONFLICT;
		}
		return OK;
	}

	// 중복된 이메일
	@GetMapping("/{email}")
	public ResponseEntity<Void> duplicationEmail(@PathVariable String email) {
		boolean duplicated = userService.duplicationEmail(email);

		if (duplicated) {
			return CONFLICT;
		}
		return OK;
	}

	// 유저 리스트 반환
	@GetMapping
	public List<UserDto> user() {
		return userService.getUserDtoList();
	}
}
