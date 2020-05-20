package com.fablab.booking.service;

import com.fablab.booking.domain.Article;
import com.fablab.booking.dto.RqCreateArticleDto;
import com.fablab.booking.dto.RqUpdateArticleDto;
import com.fablab.booking.dto.RsArticleDto;
import org.springframework.data.domain.Pageable;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface ArticleService {

    RsArticleDto save(RqCreateArticleDto rqCreateArticleDto, MultipartFile image);

    RsArticleDto update(RqUpdateArticleDto rqUpdateArticleDto, Long id);

    void deleteById(Long id);

    List<RsArticleDto> getAll(Pageable pageable);

    RsArticleDto getById(Long id);

    List<RsArticleDto> getAllByUserId(Long id, Pageable pageable);

    Article findById(Long id);

    Long count();
}
