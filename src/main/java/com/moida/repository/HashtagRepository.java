package com.moida.repository;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.moida.domain.Hashtag;
import com.moida.mapper.HashtagMapper;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class HashtagRepository {

	private final HashtagMapper hashtagMapper;

	public void insertHashtag(Hashtag hashtag) {
		hashtagMapper.insertHashtag(hashtag);
	}

	public void incrementHashtagCount(String name) {
		hashtagMapper.incrementHashtagCount(name);
	}

	public Hashtag findByName(String name) {
		return hashtagMapper.findByName(name);
	}

	public void insertFeedHashtag(int feedId, int hashtagId) {
		hashtagMapper.insertFeedHashtag(feedId, hashtagId);
	}

	public List<String> findPopularHashtags(int limit) {
		return hashtagMapper.findPopularHashtags(limit);
	}

	public List<Hashtag> findPopularHashtagsWithCount(int limit) {
		return hashtagMapper.findPopularHashtagsWithCount(limit);
	}

	public void deleteFeedHashtagsByFeedId(int feedId) {
		hashtagMapper.deleteFeedHashtagsByFeedId(feedId);
	}
}
