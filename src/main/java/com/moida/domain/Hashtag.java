package com.moida.domain;

import java.time.LocalDateTime;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class Hashtag {

	private int id;

	private String name;

	private int count;

	private LocalDateTime createdAt;

	@Builder
	public Hashtag(int id, String name, int count, LocalDateTime createdAt) {
		this.id = id;
		this.name = name;
		this.count = count;
		this.createdAt = createdAt;
	}
}
