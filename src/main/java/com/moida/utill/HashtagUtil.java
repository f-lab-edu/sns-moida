package com.moida.utill;

import java.util.HashSet;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.stereotype.Component;

@Component
public class HashtagUtil {

	private static final Pattern HASHTAG_PATTERN = Pattern.compile("#([\\p{L}\\p{N}_]+)");

	public Set<String> extractHashtags(String content) {
		Set<String> hashtags = new HashSet<>();
		if (content == null || content.trim().isEmpty()) {
			return hashtags;
		}

		Matcher matcher = HASHTAG_PATTERN.matcher(content);
		while (matcher.find()) {
			String hashtag = matcher.group(1).toLowerCase();
			if (!hashtag.isEmpty() && hashtag.length() <= 50) {
				hashtags.add(hashtag);
			}
		}

		return hashtags;
	}
}
