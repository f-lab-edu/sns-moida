package com.moida.repository;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.moida.domain.Comment;
import com.moida.mapper.CommentMapper;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class CommentRepository {

	private final CommentMapper commentMapper;

	public void insert(Comment comment) {
		commentMapper.insert(comment);
	}

	public void update(int id, Comment comment) {
		commentMapper.update(id, comment);
	}

	public void delete(int id) {
		commentMapper.delete(id);
	}

	public void deleteByFeedId(int feedId) {
		commentMapper.deleteByFeedId(feedId);
	}

	public void deleteByUserId(String userId) {
		commentMapper.deleteByUserId(userId);
	}

	public Comment findByCommentId(int id) {
		return commentMapper.findByCommentId(id);
	}

	public List<Comment> findByFeedId(int feedId) {
		return commentMapper.findByFeedId(feedId);
	}

	public List<Comment> findByUserId(String userId) {
		return commentMapper.findByUserId(userId);
	}

	public int countByFeedId(int feedId) {
		return commentMapper.countByFeedId(feedId);
	}

	public int countByUserId(String userId) {
		return commentMapper.countByUserId(userId);
	}

}
