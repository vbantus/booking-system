package com.fablab.booking.service;

import com.fablab.booking.domain.Article;
import com.fablab.booking.dto.ArticleDto;

public interface ArticleService {
    Article findById(Long id);

    ArticleDto findDtoById(Long id);

    ArticleDto save(ArticleDto articleDto);

    ArticleDto update (ArticleDto articleDto);

    void deleteById(Long id);
}
