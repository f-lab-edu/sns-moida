package com.moida.utill;

import org.springframework.stereotype.Component;

import com.moida.exception.InvalidParameterException;

@Component
public class ValidationUtil {

	public void validateId(int id) {
		if (id <= 0) {
			throw new InvalidParameterException();
		}
	}

	public void validateUserId(String userId) {
		if (userId == null || userId.trim().isEmpty()) {
			throw new InvalidParameterException();
		}
	}

	public void validateNotEmpty(String value, String fieldName) {
		if (value == null || value.trim().isEmpty()) {
			throw new InvalidParameterException();
		}
	}

	public void validateLimit(int limit) {
		if (limit <= 0 || limit > 100) {
			throw new InvalidParameterException();
		}
	}
}