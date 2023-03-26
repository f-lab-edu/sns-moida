package com.moida.response;

import java.time.LocalDateTime;

import com.moida.domain.Comment;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class CommentResponse {

	private int id;

	private int feedId;

	private String userId;

	private String content;

	@Schema(description = "생성 일자", example = "2023-02-23T10:30:00")
	private LocalDateTime createdAt;

	@Schema(description = "수정 일자", example = "2023-02-23T15:45:00")
	private LocalDateTime updatedAt;

	@Builder
	public CommentResponse(int id, int feedId, String userId, String content,
		LocalDateTime createdAt, LocalDateTime updatedAt) {
		this.id = id;
		this.feedId = feedId;
		this.userId = userId;
		this.content = content;
		this.createdAt = createdAt;
		this.updatedAt = updatedAt;
	}

	public static CommentResponse from(Comment comment) {
		return CommentResponse.builder()
			.id(comment.getId())
			.feedId(comment.getFeedId())
			.userId(comment.getUserId())
			.content(comment.getContent())
			.createdAt(comment.getCreatedAt())
			.updatedAt(comment.getUpdatedAt())
			.build();
	}
}
