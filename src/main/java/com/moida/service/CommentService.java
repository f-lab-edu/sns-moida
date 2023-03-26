package com.moida.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.moida.domain.Comment;
import com.moida.exception.CommentNotFoundException;
import com.moida.exception.InvalidParameterException;
import com.moida.repository.CommentRepository;
import com.moida.request.CreateCommentRequest;
import com.moida.request.UpdateCommentRequest;
import com.moida.response.CommentResponse;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CommentService {

	private final CommentRepository commentRepository;

	@Transactional
	public CommentResponse create(int feedId, CreateCommentRequest createCommentRequest, String userId) {
		Comment comment = createCommentRequest.toComment(feedId, userId);

		commentRepository.insert(comment);

		Comment savedComment = commentRepository.findByCommentId(comment.getId());
		return CommentResponse.from(savedComment);
	}

	@Transactional
	public CommentResponse modify(int id, UpdateCommentRequest updateCommentRequest) {
		if (id <= 0) {
			throw new InvalidParameterException();
		}

		Comment existingComment = commentRepository.findByCommentId(id);
		if (existingComment == null) {
			throw new CommentNotFoundException();
		}

		Comment comment = updateCommentRequest.toComment(existingComment);
		commentRepository.update(id, comment);

		Comment updatedComment = commentRepository.findByCommentId(id);
		return CommentResponse.from(updatedComment);
	}

	public Comment getCommentById(int id) {
		if (id <= 0) {
			throw new InvalidParameterException();
		}

		Comment comment = commentRepository.findByCommentId(id);
		if (comment == null) {
			throw new CommentNotFoundException();
		}

		return comment;
	}

	public List<Comment> getCommentsByFeedId(int feedId) {
		if (feedId <= 0) {
			throw new InvalidParameterException();
		}

		return commentRepository.findByFeedId(feedId);
	}

	public List<Comment> getCommentsByUserId(String userId) {
		if (userId == null || userId.trim().isEmpty()) {
			throw new InvalidParameterException();
		}

		return commentRepository.findByUserId(userId);
	}

	@Transactional
	public void remove(int id) {
		if (id <= 0) {
			throw new InvalidParameterException();
		}

		Comment existingComment = commentRepository.findByCommentId(id);
		if (existingComment == null) {
			throw new CommentNotFoundException();
		}

		commentRepository.delete(id);
	}
}
