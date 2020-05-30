package com.fablab.booking.service.impl;

import com.fablab.booking.domain.ArticleCategory;
import com.fablab.booking.dto.RqArticleCategoryDto;
import com.fablab.booking.dto.RsArticleCategoryDto;
import com.fablab.booking.exception.EntityNotFoundException;
import com.fablab.booking.mapper.ArticleCategoryMapper;
import com.fablab.booking.repository.ArticleCategoryRepository;
import com.fablab.booking.service.ArticleCategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ArticleCategoryServiceImpl implements ArticleCategoryService {

    private final ArticleCategoryRepository articleCategoryRepository;

    @Override
    public RsArticleCategoryDto save(RqArticleCategoryDto rqArticleCategoryDto) {
        ArticleCategory articleCategory = ArticleCategoryMapper.INSTANCE
                .rqCreateArticleCategoryDtoToArticleCategory(rqArticleCategoryDto);
        return ArticleCategoryMapper.INSTANCE
                .articleCategoryToRsArticleCategoryDto(articleCategoryRepository.save(articleCategory));
    }

    @Override
    public RsArticleCategoryDto update(RqArticleCategoryDto rqArticleCategoryDto, Long id) {
        ArticleCategory articleCategory = articleCategoryRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("article category not found by id: " + id));
        ArticleCategoryMapper.INSTANCE.updateArticleCategoryFromRqArticleCategoryDto(rqArticleCategoryDto, articleCategory);
        return ArticleCategoryMapper.INSTANCE.articleCategoryToRsArticleCategoryDto(articleCategory);
    }

    @Override
    public void deleteById(Long id) {
        articleCategoryRepository.deleteById(id);
    }

    @Override
    public List<RsArticleCategoryDto> getAll() {
        return articleCategoryRepository.findAll().stream()
                .map(ArticleCategoryMapper.INSTANCE::articleCategoryToRsArticleCategoryDto)
                .collect(Collectors.toList());
    }

    @Override
    public RsArticleCategoryDto getByName(String name) {
        return ArticleCategoryMapper.INSTANCE
                .articleCategoryToRsArticleCategoryDto(this.findByName(name));
    }

    @Override
    public ArticleCategory findByName(String name) {
        return articleCategoryRepository.findByName(name)
                .orElseThrow(() -> new EntityNotFoundException("article not found by name: " + name));
    }
}
