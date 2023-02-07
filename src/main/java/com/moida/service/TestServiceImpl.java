package com.moida.service;

import com.moida.dto.TestDto;
import com.moida.mapper.TestMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TestServiceImpl implements TestService {

    private final TestMapper testMapper;

    @Override
    public List<TestDto> getUserList() {
        return testMapper.getUserList();
    }
}
