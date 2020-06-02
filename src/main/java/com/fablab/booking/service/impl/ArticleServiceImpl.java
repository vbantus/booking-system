package com.fablab.booking.service.impl;

import com.fablab.booking.domain.Article;
import com.fablab.booking.dto.RqCreateArticleDto;
import com.fablab.booking.dto.RqUpdateArticleDto;
import com.fablab.booking.dto.RsArticleDto;
import com.fablab.booking.exception.EntityNotFoundException;
import com.fablab.booking.mapper.ArticleMapper;
import com.fablab.booking.repository.ArticleRepository;
import com.fablab.booking.service.ArticleCategoryService;
import com.fablab.booking.service.ArticleService;
import com.fablab.booking.service.MinioService;
import com.fablab.booking.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ArticleServiceImpl implements ArticleService {

    @Value("${minio.buckek.article.name}")
    private String articleBucket;

    private final ArticleRepository articleRepository;
    private final UserService userService;
    private final ArticleCategoryService articleCategoryService;
    private final MinioService minioService;

    @Override
    public RsArticleDto save(RqCreateArticleDto rqCreateArticleDto, MultipartFile image) {
        String imageUrl = minioService.saveImage(image, articleBucket);

        Article article = ArticleMapper.INSTANCE.rqCreateArticleDtoToArticle(rqCreateArticleDto);
        article.setUser(userService.findById(rqCreateArticleDto.getUserId()));
        article.setImageUrl(imageUrl);
        article.setCategory(articleCategoryService.findByName(rqCreateArticleDto.getCategory()));
        return ArticleMapper.INSTANCE.articleToRsArticleDto(articleRepository.save(article));
    }

    //TODO delete unused image
    @Override
    public RsArticleDto update(RqUpdateArticleDto rqUpdateArticleDto, MultipartFile image, Long id) {
        Article article = findById(id);
        ArticleMapper.INSTANCE.updateArticleFromRqUpdateArticleDto(rqUpdateArticleDto, article);
        article.setCategory(articleCategoryService.findByName(rqUpdateArticleDto.getCategory()));


        if (image != null) {
            String imageUrl = minioService.saveImage(image, articleBucket);
            article.setImageUrl(imageUrl);
        }
        return ArticleMapper.INSTANCE.articleToRsArticleDto(articleRepository.save(article));
    }

    @Override
    public void deleteById(Long id) {
        articleRepository.deleteById(id);
    }

    @Override
    public List<RsArticleDto> getAll(Pageable pageable) {
        return articleRepository.findAll(pageable).getContent().stream()
                .map(ArticleMapper.INSTANCE::articleToRsArticleDto)
                .collect(Collectors.toList());
    }


    @Override
    public RsArticleDto getById(Long id) {
        Article article = findById(id);
        return ArticleMapper.INSTANCE.articleToRsArticleDto(article);
    }

    @Override
    public List<RsArticleDto> getAllByUserId(Long id, Pageable pageable) {
        return articleRepository.findAllByUserId(id, pageable).stream()
                .map(ArticleMapper.INSTANCE::articleToRsArticleDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<RsArticleDto> getAllByCategoryName(String categoryName, Pageable pageable) {
        return articleRepository.findAllByCategoryName(categoryName, pageable).stream()
                .map(ArticleMapper.INSTANCE::articleToRsArticleDto)
                .collect(Collectors.toList());
    }

    @Override
    public Long countArticlesByCategoryName(String categoryName) {
        return articleRepository.countAllByCategoryName(categoryName);
    }

    @Override
    public Long count() {
        return articleRepository.count();
    }

    @Override
    public Article findById(Long id) {
        return articleRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("article not found by id: " + id));
    }
}
