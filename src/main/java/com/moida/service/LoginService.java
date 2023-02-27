package com.moida.service;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Service;

import com.moida.config.PasswordEncoder;
import com.moida.domain.User;
import com.moida.exception.LoginFailException;
import com.moida.repository.UserRepository;
import com.moida.request.LoginRequest;
import com.moida.response.UserResponse;

@Service
public class LoginService {

	private final HttpSession session;

	private final UserRepository userRepository;

	private final PasswordEncoder passwordEncoder;

	public LoginService(HttpSession session, UserRepository userRepository, PasswordEncoder passwordEncoder) {
		this.session = session;
		this.userRepository = userRepository;
		this.passwordEncoder = passwordEncoder;
	}

	public UserResponse login(LoginRequest request) {
		request.encryptPassword(passwordEncoder);

		User user = request.toUser();

		userRepository.findByUserIdAndPassword(request.getUserId(),
				request.getPassword())
			.orElseThrow(LoginFailException::new);

		session.setAttribute(request.getUserId(), user.getUserId());
		User loginUser = userRepository.findByUserId(user.getUserId());

		return UserResponse.from(loginUser);
	}

	public void logout() {
		session.invalidate();
	}
}
