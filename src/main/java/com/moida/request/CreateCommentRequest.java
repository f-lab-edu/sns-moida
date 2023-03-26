package com.moida.request;

import com.moida.domain.Comment;

import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class CreateCommentRequest {

	@NotBlank
	String content;

	@Builder
	public CreateCommentRequest(String content) {
		this.content = content;
	}

	public Comment toComment(int feedId, String userId) {
		return Comment.builder()
			.feedId(feedId)
			.userId(userId)
			.content(content)
			.build();
	}
}
