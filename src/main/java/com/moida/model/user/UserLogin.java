package com.moida.model.user;

import javax.validation.constraints.NotBlank;

import lombok.Value;

@Value
public class UserLogin {

	@NotBlank
	String userId;

	@NotBlank
	String password;
}
