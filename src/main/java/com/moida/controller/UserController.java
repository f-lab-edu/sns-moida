package com.moida.controller;

import java.util.List;

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
import com.moida.request.SingUpRequest;
import com.moida.request.UpdateUserRequest;
import com.moida.response.SignUpResponse;
import com.moida.response.UpdateUserResponse;
import com.moida.service.UserService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
public class UserController {

	private final UserService userService;

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public ResponseEntity<SignUpResponse> create(@RequestBody @Valid SingUpRequest request) {
		SignUpResponse response = userService.create(request);
		return new ResponseEntity<>(response, HttpStatus.CREATED);
	}

	@PatchMapping("/{userId}")
	public ResponseEntity<UpdateUserResponse> modify(@PathVariable String userId,
		@RequestBody @Valid UpdateUserRequest request) {
		UpdateUserResponse response = userService.modifyUser(userId, request);
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	@GetMapping("/{userId}")
	public ResponseEntity<User> getUserById(@PathVariable String userId) {
		User user = userService.getUserById(userId);
		return new ResponseEntity<>(user, HttpStatus.OK);
	}

	@DeleteMapping("/{userId}")
	public ResponseEntity<Void> remove(@PathVariable String userId) {
		userService.remove(userId);
		return new ResponseEntity<>(HttpStatus.OK);
	}

	@GetMapping
	public List<User> getUserList() {
		return userService.getUserList();
	}
}
