package com.moida.request;

import javax.validation.constraints.NotBlank;

import com.moida.domain.Feed;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class FeedRequest {

	@NotBlank
	String userId;

	@NotBlank
	String content;

	@Builder
	public FeedRequest(String userId, String content) {
		this.userId = userId;
		this.content = content;
	}

	public Feed toFeed() {
		return Feed.builder()
			.userId(userId)
			.content(content)
			.build();
	}
}
