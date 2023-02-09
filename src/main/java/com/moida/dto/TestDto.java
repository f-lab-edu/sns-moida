package com.moida.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
public class TestDto {

	private String name;
	private int age;
	private String email;

	@Builder
	public TestDto(String name, int age, String email) {
		this.name = name;
		this.age = age;
		this.email = email;
	}
}
