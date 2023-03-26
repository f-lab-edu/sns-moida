package com.moida.controller;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.moida.request.LoginRequest;
import com.moida.response.LoginResponse;
import com.moida.service.LoginService;
import com.moida.utill.JwtUtil;

import io.swagger.annotations.ApiResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;

@Tag(name = "login-controller", description = "로그인 API")
@RestController
@RequestMapping
@RequiredArgsConstructor
public class LoginController {

	private final LoginService loginService;

	private final JwtUtil jwtUtil;

	@Operation(summary = "로그인 요청", description = "로그인 됩니다.")
	@ApiResponse(code = 200, message = "ok")
	@PostMapping("/login")
	public ResponseEntity<LoginResponse> login(@RequestBody @Valid LoginRequest request) {
		LoginResponse response = loginService.login(request);
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	@Operation(summary = "로그아웃 요청", description = "로그아웃 됩니다.")
	@ApiResponse(code = 204, message = "no content")
	@GetMapping("/logout")
	public ResponseEntity<Void> logout() {
		loginService.logout();
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
}
