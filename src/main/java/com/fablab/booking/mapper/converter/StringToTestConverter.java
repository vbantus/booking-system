package com.fablab.booking.mapper.converter;

import com.fablab.booking.dto.TestDto;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class StringToTestConverter implements Converter<String, TestDto> {

    @Autowired
    private ObjectMapper objectMapper;

    @Override
    @SneakyThrows
    public TestDto convert(String source) {
        return objectMapper.readValue(source, TestDto.class);
    }
}
