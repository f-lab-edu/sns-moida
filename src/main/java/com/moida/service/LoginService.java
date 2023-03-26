package com.moida.service;

import org.springframework.stereotype.Service;

import com.moida.config.PasswordEncoder;
import com.moida.domain.User;
import com.moida.exception.LoginFailException;
import com.moida.repository.UserRepository;
import com.moida.request.LoginRequest;
import com.moida.response.LoginResponse;
import com.moida.utill.JwtUtil;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class LoginService {

	private final HttpSession session;
	private final UserRepository userRepository;
	private final PasswordEncoder passwordEncoder;
	private final JwtUtil jwtUtil;
	
	public LoginResponse login(LoginRequest request) {
		request.encryptPassword(passwordEncoder);

		User user = request.toUser();

		User loginUser = userRepository.findByUserIdAndPassword(request.getUserId(),
				request.getPassword())
			.orElseThrow(LoginFailException::new);

		session.setAttribute(request.getUserId(), loginUser.getUserId());

		String accessToken = jwtUtil.encode(loginUser.getUserId());

		return LoginResponse.builder()
			.userId(loginUser.getUserId())
			.password(loginUser.getPassword())
			.accessToken(accessToken)
			.build();
	}

	public void logout() {
		session.invalidate();
	}
}
