package com.moida.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

import com.moida.annotation.LoginCheck;
import com.moida.domain.Comment;
import com.moida.exception.InvalidParameterException;
import com.moida.request.CreateCommentRequest;
import com.moida.request.UpdateCommentRequest;
import com.moida.response.CommentResponse;
import com.moida.service.CommentService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@Tag(name = "comment-controller", description = "댓글 API")
@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class CommentController {

	private final CommentService commentService;

	@Operation(summary = "댓글 작성", description = "특정 피드에 댓글을 작성합니다.")
	@ApiResponse(responseCode = "201", description = "created")
	@LoginCheck
	@PostMapping("/feeds/{feedId}/comments")
	@ResponseStatus(HttpStatus.CREATED)
	public ResponseEntity<CommentResponse> create(
		@PathVariable int feedId,
		@RequestBody @Valid CreateCommentRequest createCommentRequest,
		jakarta.servlet.http.HttpServletRequest request) {

		String currentUserId = (String)request.getAttribute("currentUserId");
		CommentResponse response = commentService.create(feedId, createCommentRequest, currentUserId);
		return new ResponseEntity<>(response, HttpStatus.CREATED);
	}

	@Operation(summary = "피드 댓글 목록 조회", description = "특정 피드의 모든 댓글을 조회합니다.")
	@ApiResponse(responseCode = "200", description = "ok")
	@GetMapping("/feeds/{feedId}/comments")
	public ResponseEntity<List<CommentResponse>> getCommentsByFeedId(@PathVariable int feedId) {
		List<Comment> comments = commentService.getCommentsByFeedId(feedId);
		List<CommentResponse> responses = comments.stream()
			.map(CommentResponse::from)
			.toList();
		return new ResponseEntity<>(responses, HttpStatus.OK);
	}

	@Operation(summary = "댓글 조회", description = "댓글 ID로 댓글을 조회합니다.")
	@ApiResponse(responseCode = "200", description = "ok")
	@GetMapping("/comments/{id}")
	public ResponseEntity<CommentResponse> getComment(@PathVariable int id) {
		if (id <= 0) {
			throw new InvalidParameterException();
		}

		Comment comment = commentService.getCommentById(id);
		CommentResponse response = CommentResponse.from(comment);
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	@Operation(summary = "댓글 수정", description = "댓글 ID로 댓글을 수정합니다.")
	@ApiResponse(responseCode = "200", description = "updated")
	@LoginCheck
	@PatchMapping("/comments/{id}")
	public ResponseEntity<CommentResponse> update(
		@PathVariable int id,
		@RequestBody @Valid UpdateCommentRequest updateCommentRequest) {

		CommentResponse response = commentService.modify(id, updateCommentRequest);
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	@Operation(summary = "댓글 삭제", description = "댓글 ID로 댓글을 삭제합니다.")
	@LoginCheck
	@DeleteMapping("/comments/{id}")
	@ApiResponse(responseCode = "204", description = "deleted")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public ResponseEntity<Void> delete(@PathVariable int id) {
		commentService.remove(id);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
}
