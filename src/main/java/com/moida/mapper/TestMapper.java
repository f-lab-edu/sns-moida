package com.moida.mapper;

import com.moida.dto.TestDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface TestMapper {

    List<TestDto> getUserList();
}
