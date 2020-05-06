package com.fablab.booking.mapper.converter;

import com.fablab.booking.dto.RqCreateArticleDto;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class StringToRqCreateArticleDto implements Converter<String, RqCreateArticleDto> {

    private final ObjectMapper objectMapper;

    @Override
    @SneakyThrows
    public RqCreateArticleDto convert(String source) {
        return objectMapper.readValue(source, RqCreateArticleDto.class);
    }
}
