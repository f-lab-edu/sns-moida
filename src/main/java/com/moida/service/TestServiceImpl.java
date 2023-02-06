package com.moida.service;

import com.moida.dto.TestDto;
import com.moida.mapper.TestMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TestServiceImpl implements TestService {

    private final TestMapper testMapper;

    public TestServiceImpl(TestMapper testMapper) {
        this.testMapper = testMapper;
    }

    @Override
    public List<TestDto> getUserList() {
        return testMapper.getUserList();
    }
}
