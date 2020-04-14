package com.fablab.booking.service;

import com.fablab.booking.domain.Article;
import com.fablab.booking.dto.RqCreateArticleDto;
import com.fablab.booking.dto.RqUpdateArticleDto;
import com.fablab.booking.dto.RsArticleDto;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ArticleService {

    RsArticleDto save(RqCreateArticleDto rqCreateArticleDto);

    RsArticleDto update(RqUpdateArticleDto rqUpdateArticleDto, Long id);

    void deleteById(Long id);

    Article findById(Long id);

    RsArticleDto findDtoById(Long id);

    List<RsArticleDto> findAllDtoByUserId(Long id, Pageable pageable);

    List<RsArticleDto> findAllDto(Pageable pageable);
}
