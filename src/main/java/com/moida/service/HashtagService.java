package com.moida.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.moida.domain.Hashtag;
import com.moida.repository.HashtagRepository;
import com.moida.response.HashtagResponse;
import com.moida.utill.HashtagUtil;
import com.moida.utill.ValidationUtil;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class HashtagService {

	private final HashtagRepository hashtagRepository;
	private final HashtagUtil hashtagUtil;
	private final ValidationUtil validationUtil;

	public List<HashtagResponse> getPopularHashtags(int limit) {
		validationUtil.validateLimit(limit);
		List<Hashtag> popularHashtags = hashtagRepository.findPopularHashtagsWithCount(limit);

		List<HashtagResponse> result = new ArrayList<>();
		for (int i = 0; i < popularHashtags.size(); i++) {
			Hashtag hashtag = popularHashtags.get(i);
			result.add(new HashtagResponse(
				i + 1,
				hashtag.getName(),
				hashtag.getCount()
			));
		}

		return result;
	}

	public List<String> getPopularHashtagNames(int limit) {
		validationUtil.validateLimit(limit);
		return hashtagRepository.findPopularHashtags(limit);
	}

	@Transactional
	public void processHashtags(int feedId, String content) {
		validationUtil.validateId(feedId);
		validationUtil.validateNotEmpty(content, "content");

		Set<String> hashtags = hashtagUtil.extractHashtags(content);
		for (String hashtagName : hashtags) {
			processHashtag(feedId, hashtagName);
		}
	}

	@Transactional
	public void updateHashtags(int feedId, String content) {
		validationUtil.validateId(feedId);
		hashtagRepository.deleteFeedHashtagsByFeedId(feedId);
		processHashtags(feedId, content);
	}

	@Transactional
	public void removeHashtagsByFeedId(int feedId) {
		validationUtil.validateId(feedId);
		hashtagRepository.deleteFeedHashtagsByFeedId(feedId);
	}

	private void processHashtag(int feedId, String hashtagName) {
		Hashtag existingHashtag = hashtagRepository.findByName(hashtagName);

		if (existingHashtag == null) {
			Hashtag newHashtag = Hashtag.builder()
				.name(hashtagName)
				.count(1)
				.build();
			hashtagRepository.insertHashtag(newHashtag);
			hashtagRepository.insertFeedHashtag(feedId, newHashtag.getId());
		} else {
			hashtagRepository.incrementHashtagCount(hashtagName);
			hashtagRepository.insertFeedHashtag(feedId, existingHashtag.getId());
		}
	}
}