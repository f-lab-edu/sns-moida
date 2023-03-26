package com.moida.request;

import com.moida.domain.Feed;

import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class CreateFeedRequest {

	@NotBlank
	String content;

	@Builder
	public CreateFeedRequest(String content) {
		this.content = content;
	}

	public Feed toFeed(String userId) {
		return Feed.builder()
			.userId(userId)
			.content(content)
			.build();
	}
}
