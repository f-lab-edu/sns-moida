package com.moida.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.moida.domain.User;
import com.moida.exception.InvalidParameterException;
import com.moida.exception.InvalidSearchKeywordException;
import com.moida.exception.UserNotFoundException;
import com.moida.request.CreateUserRequest;
import com.moida.request.UpdateUserRequest;
import com.moida.response.UserResponse;
import com.moida.service.UserService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@Tag(name = "user-controller", description = "회원 API")
@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
public class UserController {

	private final UserService userService;

	@Operation(summary = "회원 가입 요청", description = "회원 가입을 합니다.")
	@ApiResponse(responseCode = "201", description = "created")
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public ResponseEntity<UserResponse> create(@RequestBody @Valid CreateUserRequest request) {
		UserResponse response = userService.create(request);
		return new ResponseEntity<>(response, HttpStatus.CREATED);
	}

	@Operation(summary = "회원 정보 수정 요청", description = "회원 정보를 수정합니다.")
	@ApiResponse(responseCode = "200", description = "ok")
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
	@ApiResponse(responseCode = "200", description = "ok")
	@GetMapping("/{userId}")
	public ResponseEntity<UserResponse> getUserById(@PathVariable String userId) {
		User user = userService.getUserById(userId);
		if (user == null) {
			throw new UserNotFoundException();
		}

		UserResponse userResponse = UserResponse.from(user);
		return new ResponseEntity<>(userResponse, HttpStatus.OK);
	}

	@Operation(summary = "회원 탈퇴 요청", description = "회원 정보가 삭제됩니다.")
	@ApiResponse(responseCode = "204", description = "deleted")
	@DeleteMapping("/{userId}")
	public ResponseEntity<Void> remove(@PathVariable String userId) {
		User user = userService.getUserById(userId);
		if (user == null) {
			throw new UserNotFoundException();
		}

		userService.remove(userId);
		return new ResponseEntity<>(HttpStatus.OK);
	}

	@Operation(summary = "회원 리스트 요청", description = "사용자 전체를 조회한다.")
	@ApiResponse(responseCode = "200", description = "ok")
	@GetMapping
	public ResponseEntity<List<UserResponse>> getUserList() {
		List<User> users = userService.getUserList();
		List<UserResponse> response = users.stream()
			.map(UserResponse::from)
			.collect(Collectors.toList());
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	@Operation(summary = "키워드로 사용자 검색", description = "이름 또는 닉네임으로 사용자를 검색합니다.")
	@ApiResponse(responseCode = "200", description = "ok")
	@GetMapping("/search")
	public ResponseEntity<List<UserResponse>> searchUsers(@RequestParam String keyword) {
		if (keyword == null || keyword.trim().isEmpty()) {
			throw new InvalidSearchKeywordException();
		}

		List<User> users = userService.searchByKeyword(keyword);
		List<UserResponse> response = users.stream()
			.map(UserResponse::from)
			.collect(Collectors.toList());
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	@Operation(summary = "이름으로 사용자 검색", description = "정확한 이름으로 사용자를 검색합니다.")
	@ApiResponse(responseCode = "200", description = "ok")
	@GetMapping("/search/name")
	public ResponseEntity<List<UserResponse>> searchUsersByName(@RequestParam String name) {
		if (name == null || name.trim().isEmpty()) {
			throw new InvalidSearchKeywordException();
		}

		List<User> users = userService.searchByName(name);
		List<UserResponse> response = users.stream()
			.map(UserResponse::from)
			.collect(Collectors.toList());
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	@Operation(summary = "닉네임으로 사용자 검색", description = "닉네임으로 사용자를 검색합니다.")
	@ApiResponse(responseCode = "200", description = "ok")
	@GetMapping("/search/nickname")
	public ResponseEntity<List<UserResponse>> searchUsersByNickname(@RequestParam String nickname) {
		if (nickname == null || nickname.trim().isEmpty()) {
			throw new InvalidSearchKeywordException();
		}

		List<User> users = userService.searchByNickname(nickname);
		List<UserResponse> response = users.stream()
			.map(UserResponse::from)
			.collect(Collectors.toList());
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	@Operation(summary = "추천 사용자", description = "팔로우 추천 사용자 목록을 반환합니다.")
	@ApiResponse(responseCode = "200", description = "ok")
	@GetMapping("/recommended")
	public ResponseEntity<List<UserResponse>> getRecommendedUsers(
		@RequestParam(defaultValue = "10") int limit) {

		if (limit <= 0 || limit > 100) {
			throw new InvalidParameterException();
		}

		List<User> users = userService.getRecommendedUsers(limit);
		List<UserResponse> response = users.stream()
			.map(UserResponse::from)
			.collect(Collectors.toList());

		return new ResponseEntity<>(response, HttpStatus.OK);
	}
}
