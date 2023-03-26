package com.moida.response;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class HashtagResponse {
	private int rank;
	private String hashtag;
	private int count;

	@Builder
	public HashtagResponse(int rank, String hashtag, int count) {
		this.rank = rank;
		this.hashtag = hashtag;
		this.count = count;
	}
}
