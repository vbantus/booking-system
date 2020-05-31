package com.fablab.booking.service;

import com.fablab.booking.domain.ArticleCategory;
import com.fablab.booking.dto.RqArticleCategoryDto;
import com.fablab.booking.dto.RsArticleCategoryDto;

import java.util.List;

public interface ArticleCategoryService {

    RsArticleCategoryDto save(RqArticleCategoryDto rqArticleCategoryDto);

    RsArticleCategoryDto update(RqArticleCategoryDto rqArticleCategoryDto, Long id);

    void deleteById(Long id);

    List<RsArticleCategoryDto> getAll();

    RsArticleCategoryDto getByName(String name);

    ArticleCategory findByName(String name);
}
