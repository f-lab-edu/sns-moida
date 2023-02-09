package com.moida.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.moida.dto.TestDto;
import com.moida.mapper.TestMapper;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class TestServiceImpl implements TestService {

	private final TestMapper testMapper;

	@Override
	public List<TestDto> getUserList() {
		return testMapper.getUserList();
	}
}
