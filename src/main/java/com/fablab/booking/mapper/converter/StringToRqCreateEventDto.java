package com.fablab.booking.mapper.converter;

import com.fablab.booking.dto.RqCreateEventDto;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class StringToRqCreateEventDto implements Converter<String, RqCreateEventDto> {

    private final ObjectMapper objectMapper;

    @Override
    @SneakyThrows
    public RqCreateEventDto convert(String source) {
        return objectMapper.readValue(source, RqCreateEventDto.class);
    }
}
