package com.moida.controller;

import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.moida.annotation.LoginCheck;
import com.moida.domain.Feed;
import com.moida.exception.FeedNotFoundException;
import com.moida.request.FeedRequest;
import com.moida.response.FeedResponse;
import com.moida.service.FeedService;

import io.swagger.annotations.ApiResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;

@Tag(name = "feed-controller", description = "피드 API")
@RestController
@RequiredArgsConstructor
@RequestMapping("/feeds")
public class FeedController {

	private final FeedService feedService;

	@Operation(summary = "피드 생성 요청", description = "피드를 생성 합니다.")
	@ApiResponse(code = 201, message = "created")
	@LoginCheck
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public ResponseEntity<FeedResponse> create(@RequestBody @Valid FeedRequest request) {
		FeedResponse response = feedService.create(request);
		return new ResponseEntity<>(response, HttpStatus.CREATED);
	}

	@Operation(summary = "피드 정보 요청", description = "피드 id를 검색해서 정보를 가져옵니다.")
	@ApiResponse(code = 200, message = "ok")
	@GetMapping("/{id}")
	public ResponseEntity<FeedResponse> getFeedId(@PathVariable int id) {
		Feed feed = feedService.getFeedId(id);
		FeedResponse response = FeedResponse.from(feed);
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	@Operation(summary = "피드수정 요청", description = "피드 정보를 수정합니다.")
	@ApiResponse(code = 200, message = "ok")
	@LoginCheck
	@PatchMapping("/{id}")
	public ResponseEntity<FeedResponse> modify(@PathVariable int id, @RequestBody @Valid FeedRequest request) {
		Feed feed = feedService.getFeedId(id);
		if (feed == null) {
			throw new FeedNotFoundException();
		}

		FeedResponse response = feedService.modify(id, request);
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	@Operation(summary = "피드 삭제 요청", description = "피드가 삭제됩니다.")
	@LoginCheck
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> remove(@PathVariable int id) {
		feedService.remove(id);
		return new ResponseEntity<>(HttpStatus.OK);
	}

	@Operation(summary = "피드 리스트 요청", description = "피드 전체를 조회한다.")
	@ApiResponse(code = 200, message = "ok")
	@GetMapping
	private List<FeedResponse> getUserFeedList() {
		return feedService.getUserFeedList().stream()
			.map(FeedResponse::from)
			.collect(Collectors.toList());
	}

}
