package com.moida.response;

import java.time.LocalDateTime;

import com.moida.domain.Feed;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class FeedResponse {

	private String userId;

	private String content;

	@Schema(description = "생성 일자", example = "1996-05-13")
	private LocalDateTime createdAt;

	@Schema(description = "수정 일자", example = "2023-02-23")
	private LocalDateTime updatedAt;

	@Builder
	public FeedResponse(String userId, String content, LocalDateTime createdAt, LocalDateTime updatedAt) {
		this.userId = userId;
		this.content = content;
		this.createdAt = createdAt;
		this.updatedAt = updatedAt;
	}

	public static FeedResponse from(Feed feed) {
		return FeedResponse.builder()
			.userId(feed.getUserId())
			.content(feed.getContent())
			.createdAt(feed.getCreatedAt())
			.updatedAt(feed.getUpdatedAt())
			.build();
	}
}
