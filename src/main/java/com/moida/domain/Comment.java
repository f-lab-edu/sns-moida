package com.moida.domain;

import java.time.LocalDateTime;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class Comment {

	private int id;

	private int feedId;

	private String userId;

	private String content;

	private LocalDateTime createdAt;

	private LocalDateTime updatedAt;

	@Builder
	public Comment(int id, int feedId, String userId, String content, LocalDateTime createdAt,
		LocalDateTime updatedAt) {
		this.id = id;
		this.feedId = feedId;
		this.userId = userId;
		this.content = content;
		this.createdAt = createdAt;
		this.updatedAt = updatedAt;
	}

}
