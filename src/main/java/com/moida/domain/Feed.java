package com.moida.domain;

import java.time.LocalDateTime;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class Feed {

	private int id;

	private String userId;

	private String content;

	private LocalDateTime createdAt;

	private LocalDateTime updatedAt;

	@Builder
	public Feed(int id, String userId, String content, LocalDateTime createdAt, LocalDateTime updatedAt) {
		this.id = id;
		this.userId = userId;
		this.content = content;
		this.createdAt = createdAt;
		this.updatedAt = updatedAt;
	}
}
