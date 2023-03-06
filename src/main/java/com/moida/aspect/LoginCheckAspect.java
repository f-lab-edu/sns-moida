package com.moida.aspect;

import javax.servlet.http.HttpSession;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

import com.moida.exception.LoginRequiredException;
import com.moida.request.FeedRequest;

@Component
@Aspect
public class LoginCheckAspect {

	private final HttpSession session;

	public LoginCheckAspect(HttpSession session) {
		this.session = session;
	}

	@Before("@annotation(com.moida.annotation.LoginCheck)")
	public void checkLogin(JoinPoint joinPoint) {

		String userId = (String)session.getAttribute(((FeedRequest)joinPoint.getArgs()[0]).getUserId());

		if (userId == null) {
			throw new LoginRequiredException();
		}
	}
}
