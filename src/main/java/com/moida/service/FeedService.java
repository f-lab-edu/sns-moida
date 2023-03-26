package com.moida.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.moida.domain.Feed;
import com.moida.exception.FeedNotFoundException;
import com.moida.exception.InvalidParameterException;
import com.moida.repository.FeedRepository;
import com.moida.request.CreateFeedRequest;
import com.moida.request.UpdateFeedRequest;
import com.moida.response.FeedResponse;
import com.moida.utill.ValidationUtil;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class FeedService {

	private final FeedRepository feedRepository;
	private final HashtagService hashtagService;
	private final ValidationUtil validationUtil;

	@Transactional
	public FeedResponse create(CreateFeedRequest createFeedRequest, String userId) {
		Feed feed = createFeedRequest.toFeed(userId);

		feedRepository.insert(feed);
		hashtagService.processHashtags(feed.getId(), feed.getContent());

		Feed savedFeed = feedRepository.findByFeedId(feed.getId());
		return FeedResponse.from(savedFeed);
	}

	@Transactional
	public FeedResponse modify(int id, UpdateFeedRequest updateFeedRequest) {
		validateFeedId(id);

		Feed existingFeed = getFeedById(id);
		Feed feed = updateFeedRequest.toFeed(existingFeed);

		feedRepository.update(id, feed);
		hashtagService.updateHashtags(id, feed.getContent());

		Feed updatedFeed = feedRepository.findByFeedId(id);
		return FeedResponse.from(updatedFeed);
	}

	public Feed getFeedById(int id) {
		validateFeedId(id);

		Feed feed = feedRepository.findByFeedId(id);
		if (feed == null) {
			throw new FeedNotFoundException();
		}

		return feed;
	}

	@Transactional
	public void remove(int id) {
		validateFeedId(id);
		getFeedById(id); // 존재 여부 확인

		hashtagService.removeHashtagsByFeedId(id);
		feedRepository.delete(id);
	}

	public List<Feed> getUserFeedList() {
		return feedRepository.findByUserFeedList();
	}

	public List<Feed> getFeedsByUserId(String userId) {
		return feedRepository.findByUserId(userId);
	}

	public List<Feed> searchFeeds(String keyword, String searchType) {
		if (keyword == null || keyword.trim().isEmpty()) {
			return getUserFeedList();
		}

		keyword = keyword.trim().toLowerCase();

		return switch (searchType.toLowerCase()) {
			case "hashtag" -> searchFeedsByHashtag(keyword);
			case "content" -> searchFeedsByContent(keyword);
			default -> searchFeedsByAll(keyword);
		};
	}

	private List<Feed> searchFeedsByHashtag(String keyword) {

		String hashtag = keyword.startsWith("#") ? keyword.substring(1) : keyword;
		return feedRepository.findByHashtag(hashtag);
	}

	private List<Feed> searchFeedsByContent(String keyword) {

		return feedRepository.findByContentContaining(keyword);
	}

	private List<Feed> searchFeedsByAll(String keyword) {

		return feedRepository.findByKeyword(keyword);
	}

	private void validateFeedId(int id) {
		validationUtil.validateId(id);
	}

}
