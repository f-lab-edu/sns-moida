package com.moida.aspect;

import java.util.Objects;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.moida.exception.LoginRequiredException;
import com.moida.utill.JwtUtil;

import io.jsonwebtoken.Claims;
import jakarta.servlet.http.HttpServletRequest;

@Aspect
@Component
public class LoginCheckAspect {

	private static final String BEARER_PREFIX = "Bearer ";
	private static final int BEARER_PREFIX_LENGTH = BEARER_PREFIX.length();

	private JwtUtil jwtUtil;

	public LoginCheckAspect(JwtUtil jwtUtil) {
		this.jwtUtil = jwtUtil;
	}

	@Before("@annotation(com.moida.annotation.LoginCheck)")
	public void checkLogin(JoinPoint joinPoint) {
		ServletRequestAttributes attributes = (ServletRequestAttributes)RequestContextHolder.getRequestAttributes();
		HttpServletRequest request = Objects.requireNonNull(attributes).getRequest();
		String token = request.getHeader("Authorization");

		if (token == null || !token.startsWith(BEARER_PREFIX)) {
			throw new LoginRequiredException();
		}

		Claims claims = jwtUtil.decode(token.substring(BEARER_PREFIX_LENGTH));
		String userId = claims.get("userId", String.class);
		
		request.setAttribute("currentUserId", userId);
	}
}
