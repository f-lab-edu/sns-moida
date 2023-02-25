package com.moida.controller;

import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.moida.domain.User;
import com.moida.exception.UserNotFoundException;
import com.moida.request.SingUpRequest;
import com.moida.request.UpdateUserRequest;
import com.moida.response.UserResponse;
import com.moida.service.UserService;

import io.swagger.annotations.ApiResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;

@Tag(name = "user-controller", description = "회원 API")
@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
public class UserController {

	private final UserService userService;

	@Operation(summary = "회원 가입 요청", description = "회원 가입을 합니다.")
	@ApiResponse(code = 201, message = "created")
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public ResponseEntity<UserResponse> create(@RequestBody @Valid SingUpRequest request) {
		UserResponse response = userService.create(request);
		return new ResponseEntity<>(response, HttpStatus.CREATED);
	}

	@Operation(summary = "회원 정보 수정 요청", description = "회원 정보를 수정합니다.")
	@ApiResponse(code = 200, message = "ok")
	@PatchMapping("/{userId}")
	public ResponseEntity<UserResponse> modify(@PathVariable String userId,
		@RequestBody @Valid UpdateUserRequest request) {
		User user = userService.getUserById(userId);
		if (user == null) {
			throw new UserNotFoundException();
		}

		UserResponse response = userService.modifyUser(userId, request);
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	@Operation(summary = "회원 정보 요청", description = "회원 id를 검색해서 정보를 ")
	@ApiResponse(code = 200, message = "ok")
	@GetMapping("/{userId}")
	public ResponseEntity<UserResponse> getUserById(@PathVariable String userId) {
		User user = userService.getUserById(userId);
		UserResponse userResponse = UserResponse.from(user);
		return new ResponseEntity<>(userResponse, HttpStatus.OK);
	}

	@Operation(summary = "회원 탈퇴 요청", description = "회원 정보가 삭제됩니다.")
	@ApiResponse(code = 200, message = "ok")
	@DeleteMapping("/{userId}")
	public ResponseEntity<Void> remove(@PathVariable String userId) {
		userService.remove(userId);
		return new ResponseEntity<>(HttpStatus.OK);
	}

	@Operation(summary = "회원 리스트 요청", description = "사용자 전체를 조회한다.")
	@ApiResponse(code = 200, message = "ok")
	@GetMapping
	public List<UserResponse> getUserList() {
		return userService.getUserList().stream()
			.map(UserResponse::from)
			.collect(Collectors.toList());
	}
}
