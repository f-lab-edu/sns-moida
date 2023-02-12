package com.moida.model.user;

import javax.validation.constraints.NotBlank;

import lombok.Value;

@Value
public class UserLogin {

	@NotBlank
	String user_id;

	@NotBlank
	String password;
}
