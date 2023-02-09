package com.moida.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.moida.dto.TestDto;

@Mapper
public interface TestMapper {

	List<TestDto> getUserList();
}
