package com.moida.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.moida.domain.Comment;

@Mapper
public interface CommentMapper {

	void insert(Comment comment);

	void update(@Param("id") int id, @Param("comment") Comment comment);

	void delete(int id);

	void deleteByFeedId(int feedId);

	void deleteByUserId(String userId);

	Comment findByCommentId(int id);

	List<Comment> findByFeedId(int feedId);

	List<Comment> findByUserId(String userId);

	int countByFeedId(int feedId);

	int countByUserId(String userId);
}
