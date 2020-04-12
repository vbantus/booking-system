package com.fablab.booking.service.impl;

import com.fablab.booking.domain.Article;
import com.fablab.booking.dto.RqCreateArticleDto;
import com.fablab.booking.dto.RqUpdateArticleDto;
import com.fablab.booking.dto.RsArticleDto;
import com.fablab.booking.mapper.ArticleMapper;
import com.fablab.booking.repository.ArticleRepository;
import com.fablab.booking.repository.UserRepository;
import com.fablab.booking.service.ArticleService;
import com.fablab.booking.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ArticleServiceImpl implements ArticleService {

    private final ArticleRepository articleRepository;
    private final UserService userService;

    @Override
    public Article findById(Long id) {
        return articleRepository.findById(id).orElse(null);
    }

    @Override
    public RsArticleDto findDtoById(Long id) {
        Article article = articleRepository.findById(id).orElse(null);
        return ArticleMapper.INSTANCE.articleToRsArticleDto(article);
    }

    @Override
    public List<RsArticleDto> findAllDtoByUserId(Long id) {
        return articleRepository.findAllByUserId(id).stream()
                .map(ArticleMapper.INSTANCE::articleToRsArticleDto)
                .collect(Collectors.toList());
    }

    @Override
    public RsArticleDto save(RqCreateArticleDto rqCreateArticleDto) {
        Article article = ArticleMapper.INSTANCE.rqCreateArticleDtoToArticle(rqCreateArticleDto);
        article.setUser(userService.findById(rqCreateArticleDto.getUserId()));
        return ArticleMapper.INSTANCE.articleToRsArticleDto(articleRepository.save(article));
    }

    @Override
    public RsArticleDto update(RqUpdateArticleDto rqUpdateArticleDto, Long id) {
        Article article = articleRepository.findById(id).orElse(null);
        ArticleMapper.INSTANCE.updateArticleFromRqUpdateArticleDto(rqUpdateArticleDto, article);
        return ArticleMapper.INSTANCE.articleToRsArticleDto(articleRepository.save(article));
    }

    @Override
    public void deleteById(Long id) {
        articleRepository.deleteById(id);
    }
}
