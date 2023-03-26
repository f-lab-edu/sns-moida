package com.moida.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.moida.domain.Hashtag;

@Mapper
public interface HashtagMapper {

	void insertHashtag(Hashtag hashtag);

	void incrementHashtagCount(@Param("name") String name);

	Hashtag findByName(@Param("name") String name);

	void insertFeedHashtag(@Param("feedId") int feedId, @Param("hashtagId") int hashtagId);

	List<String> findPopularHashtags(@Param("limit") int limit);

	List<Hashtag> findPopularHashtagsWithCount(@Param("limit") int limit);

	void deleteFeedHashtagsByFeedId(@Param("feedId") int feedId);
}