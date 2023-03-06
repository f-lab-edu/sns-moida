package com.moida.aspect;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.moida.exception.LoginFailException;

@Component
@Aspect
public class LoginCheckAspect {

	private final HttpSession session;

	public LoginCheckAspect(HttpSession session) {
		this.session = session;
	}

	@Before("@annotation(com.moida.annotation.LoginCheck)")
	public void checkLogin(JoinPoint joinPoint) {
		ServletRequestAttributes attributes = (ServletRequestAttributes)RequestContextHolder.getRequestAttributes();
		HttpServletRequest request = attributes.getRequest();

		String userId = (String)session.getAttribute(request.getParameter("userId"));

		if (userId == null) {
			throw new LoginFailException("로그인이 필요합니다.");
		}
	}
}
