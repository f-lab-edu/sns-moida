package com.moida.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.moida.domain.Feed;

@Mapper
public interface FeedMapper {

	void insert(Feed feed);

	void update(@Param("id") int id, @Param("feed") Feed feed);

	void delete(int id);

	Feed findByFeedId(Integer id);

	List<Feed> findByUserFeedList();
}
