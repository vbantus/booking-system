package com.fablab.booking.service.impl;

import com.fablab.booking.domain.Article;
import com.fablab.booking.dto.ArticleDto;
import com.fablab.booking.mapper.ArticleMapper;
import com.fablab.booking.repository.ArticleRepository;
import com.fablab.booking.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ArticleServiceImpl implements ArticleService {

    private final ArticleRepository articleRepository;

    @Autowired
    public ArticleServiceImpl(ArticleRepository articleRepository) {
        this.articleRepository = articleRepository;
    }

    @Override
    public Article findById(Long id) {
        return articleRepository.findById(id).orElse(null);
    }

    @Override
    public ArticleDto findDtoById(Long id) {
        Article article = articleRepository.findById(id).orElse(null);
        return ArticleMapper.INSTANCE.articleToArticleDto(article);
    }

    @Override
    public ArticleDto save(ArticleDto articleDto) {
        Article article = ArticleMapper.INSTANCE.articleDtoToArticle(articleDto);
        return ArticleMapper.INSTANCE.articleToArticleDto(articleRepository.save(article));
    }

    @Override
    public ArticleDto update(ArticleDto articleDto) {
        Article article = articleRepository.findById(articleDto.getId()).orElse(null);
        ArticleMapper.INSTANCE.updateArticleFromArticleDto(articleDto, article);
        return ArticleMapper.INSTANCE.articleToArticleDto(articleRepository.save(article));
    }

    @Override
    public void deleteById(Long id) {
        articleRepository.deleteById(id);
    }
}
