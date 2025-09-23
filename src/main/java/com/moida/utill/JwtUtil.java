package com.moida.utill;

import java.security.Key;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import io.jsonwebtoken.security.SignatureException;

@Component
public class JwtUtil {
	private final Key key;

	public JwtUtil(@Value("${jwt.secret}") String secret) {
		key = Keys.hmacShaKeyFor(secret.getBytes());
	}

	public String encode(String userId) {

		return Jwts.builder()
			.claim("userId", userId)
			.signWith(key)
			.compact();
	}

	public boolean validate(String token) {
		if (token == null || token.isBlank()) {
			return false;
		}
		try {
			Jwts.parserBuilder()
				.setSigningKey(key)
				.build()
				.parseClaimsJws(token);
			return true;
		} catch (SignatureException e) {
			return false;
		}
	}

	public Claims decode(String token) {
		return Jwts.parserBuilder()
			.setSigningKey(key)
			.build()
			.parseClaimsJws(token)
			.getBody();
	}
}
