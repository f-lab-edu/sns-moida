package com.moida.request;

import com.moida.domain.Feed;

import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class UpdateFeedRequest {

	@NotBlank
	String content;

	@Builder
	public UpdateFeedRequest(String content) {
		this.content = content;
	}

	public Feed toFeed(Feed existingFeed) {
		return Feed.builder()
			.id(existingFeed.getId())
			.userId(existingFeed.getUserId())
			.content(content)
			.createdAt(existingFeed.getCreatedAt())
			.build();
	}
}
