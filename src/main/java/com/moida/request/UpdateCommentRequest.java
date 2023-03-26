package com.moida.request;

import com.moida.domain.Comment;

import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class UpdateCommentRequest {

	@NotBlank
	String content;

	@Builder
	public UpdateCommentRequest(String content) {
		this.content = content;
	}

	public Comment toComment(Comment existingComment) {
		return Comment.builder()
			.id(existingComment.getId())
			.feedId(existingComment.getFeedId())
			.userId(existingComment.getUserId())
			.content(content)
			.createdAt(existingComment.getCreatedAt())
			.build();
	}
}
