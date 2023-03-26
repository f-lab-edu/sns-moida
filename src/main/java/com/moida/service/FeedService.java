package com.moida.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.moida.domain.Feed;
import com.moida.repository.FeedRepository;
import com.moida.request.FeedRequest;
import com.moida.response.FeedResponse;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class FeedService {

	private final FeedRepository feedRepository;

	@Transactional
	public FeedResponse create(FeedRequest request) {

		Feed feed = request.toFeed();

		feedRepository.insert(feed);

		Feed savedFeed = feedRepository.findByFeedId(feed.getId());
		return FeedResponse.from(savedFeed);
	}

	@Transactional
	public FeedResponse modify(int id, FeedRequest request) {
		Feed feed = request.toFeed();

		feedRepository.update(id, feed);

		Feed updateFeed = feedRepository.findByFeedId(id);
		return FeedResponse.from(updateFeed);
	}

	public Feed getFeedId(int id) {
		return feedRepository.findByFeedId(id);
	}

	public List<Feed> getUserFeedList() {
		return feedRepository.findByUserFeedList();
	}

	public void remove(int id) {
		feedRepository.delete(id);
	}
}
