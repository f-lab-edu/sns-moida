package com.moida.repository;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.moida.domain.Feed;
import com.moida.mapper.FeedMapper;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class FeedRepository {

	public final FeedMapper feedMapper;

	public void insert(Feed feed) {
		feedMapper.insert(feed);
	}

	public void update(int id, Feed feed) {
		feedMapper.update(id, feed);
	}

	public void delete(int id) {
		feedMapper.delete(id);
	}

	public Feed findByFeedId(int id) {
		return feedMapper.findByFeedId(id);
	}

	public List<Feed> findByUserFeedList() {
		return feedMapper.findByUserFeedList();
	}

	public List<Feed> findByUserId(String userId) {
		return feedMapper.findByUserId(userId);
	}

	public List<Feed> findByHashtag(String hashtag) {
		return feedMapper.findByHashtag(hashtag);
	}

	public List<Feed> findByContentContaining(String keyword) {
		return feedMapper.findByContentContaining(keyword);
	}

	public List<Feed> findByKeyword(String keyword) {
		return feedMapper.findByKeyword(keyword);
	}

}
