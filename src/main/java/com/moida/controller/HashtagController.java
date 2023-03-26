package com.moida.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.moida.response.HashtagResponse;
import com.moida.service.HashtagService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;

@Tag(name = "hashtag-controller", description = "해시태그 API")
@RestController
@RequiredArgsConstructor
@RequestMapping("/hashtags")
public class HashtagController extends BaseController {

	private final HashtagService hashtagService;

	@Operation(summary = "인기 해시태그 순위 조회", description = "사용 빈도가 높은 해시태그를 순위별로 조회합니다.")
	@ApiResponse(responseCode = "200", description = "ok")
	@GetMapping("/popular")
	public ResponseEntity<List<HashtagResponse>> getPopularHashtags(
		@RequestParam(name = "limit", defaultValue = "10") int limit) {
		List<HashtagResponse> popularHashtags = hashtagService.getPopularHashtags(limit);
		return ok(popularHashtags);
	}

	@Operation(summary = "인기 해시태그 이름 목록 조회", description = "사용 빈도가 높은 해시태그 이름만 조회합니다.")
	@ApiResponse(responseCode = "200", description = "ok")
	@GetMapping("/popular/names")
	public ResponseEntity<List<String>> getPopularHashtagNames(
		@RequestParam(name = "limit", defaultValue = "10") int limit) {
		List<String> popularHashtagNames = hashtagService.getPopularHashtagNames(limit);
		return ok(popularHashtagNames);
	}
}
