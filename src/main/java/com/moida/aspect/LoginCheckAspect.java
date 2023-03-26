package com.moida.aspect;

import javax.servlet.http.HttpServletRequest;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.moida.exception.LoginRequiredException;
import com.moida.utill.JwtUtil;

@Aspect
@Component
public class LoginCheckAspect {

	private JwtUtil jwtUtil;

	public LoginCheckAspect(JwtUtil jwtUtil) {
		this.jwtUtil = jwtUtil;
	}

	@Before("@annotation(com.moida.annotation.LoginCheck)")
	public void checkLogin(JoinPoint joinPoint) {
		ServletRequestAttributes attributes = (ServletRequestAttributes)RequestContextHolder.getRequestAttributes();
		HttpServletRequest request = attributes.getRequest();
		String token = request.getHeader("Authorization");

		if (token == null || !token.startsWith("Bearer ")) {
			throw new LoginRequiredException();
		}

		jwtUtil.decode(token.substring((7)));
	}
}
