package com.moida.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.moida.annotation.LoginCheck;
import com.moida.domain.Feed;
import com.moida.request.CreateFeedRequest;
import com.moida.request.UpdateFeedRequest;
import com.moida.response.FeedResponse;
import com.moida.service.FeedService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@Tag(name = "feed-controller", description = "피드 API")
@RestController
@RequiredArgsConstructor
@RequestMapping("/feeds")
public class FeedController extends BaseController {

	private final FeedService feedService;

	@Operation(summary = "피드 생성 요청", description = "피드를 생성 합니다.")
	@ApiResponse(responseCode = "201", description = "created")
	@LoginCheck
	@PostMapping
	public ResponseEntity<FeedResponse> create(@RequestBody @Valid CreateFeedRequest createFeedRequest,
		HttpServletRequest request) {
		String currentUserId = (String)request.getAttribute("currentUserId");
		FeedResponse response = feedService.create(createFeedRequest, currentUserId);
		return created(response);
	}

	@Operation(summary = "피드 정보 요청", description = "피드 id를 검색해서 정보를 가져옵니다.")
	@ApiResponse(responseCode = "200", description = "ok")
	@GetMapping("/{id}")
	public ResponseEntity<FeedResponse> getFeedById(@PathVariable int id) {
		Feed feed = feedService.getFeedById(id);
		FeedResponse response = FeedResponse.from(feed);
		return ok(response);
	}

	@Operation(summary = "피드수정 요청", description = "피드 정보를 수정합니다.")
	@ApiResponse(responseCode = "200", description = "ok")
	@LoginCheck
	@PatchMapping("/{id}")
	public ResponseEntity<FeedResponse> modify(@PathVariable int id,
		@RequestBody @Valid UpdateFeedRequest updateFeedRequest) {
		FeedResponse response = feedService.modify(id, updateFeedRequest);
		return ok(response);
	}

	@Operation(summary = "피드 삭제 요청", description = "피드가 삭제됩니다.")
	@ApiResponse(responseCode = "204", description = "deleted")
	@LoginCheck
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> remove(@PathVariable int id) {
		feedService.remove(id);
		return noContent();
	}

	@Operation(summary = "피드 리스트 요청", description = "피드 전체를 조회한다.")
	@ApiResponse(responseCode = "200", description = "ok")
	@GetMapping
	public ResponseEntity<List<FeedResponse>> getUserFeedList() {
		List<FeedResponse> response = feedService.getUserFeedList().stream()
			.map(FeedResponse::from)
			.collect(Collectors.toList());

		return ok(response);
	}

	@Operation(summary = "피드 검색", description = "키워드로 피드를 검색합니다. 해시태그 또는 내용 검색 가능")
	@ApiResponse(responseCode = "200", description = "ok")
	@GetMapping("/search")
	public ResponseEntity<List<FeedResponse>> searchFeeds(@RequestParam(name = "keyword") String keyword,
		@RequestParam(name = "type", defaultValue = "all") String searchType) {
		List<Feed> feeds = feedService.searchFeeds(keyword, searchType);
		List<FeedResponse> response = feeds.stream()
			.map(FeedResponse::from)
			.collect(Collectors.toList());
		return ok(response);
	}

	@Operation(summary = "특정 유저의 피드 조회", description = "특정 유저가 작성한 피드 목록을 조회합니다.")
	@ApiResponse(responseCode = "200", description = "ok")
	@GetMapping("/user/{userId}")
	public ResponseEntity<List<FeedResponse>> getFeedsByUserId(@PathVariable String userId) {
		List<Feed> feeds = feedService.getFeedsByUserId(userId);
		List<FeedResponse> response = feeds.stream()
			.map(FeedResponse::from)
			.collect(Collectors.toList());
		return ok(response);
	}

}
