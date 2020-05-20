package com.fablab.booking.service.impl;

import com.fablab.booking.domain.Article;
import com.fablab.booking.domain.common.exception.EntityNotFoundException;
import com.fablab.booking.dto.RqCreateArticleDto;
import com.fablab.booking.dto.RqUpdateArticleDto;
import com.fablab.booking.dto.RsArticleDto;
import com.fablab.booking.mapper.ArticleMapper;
import com.fablab.booking.repository.ArticleRepository;
import com.fablab.booking.service.ArticleService;
import com.fablab.booking.service.MinioService;
import com.fablab.booking.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ArticleServiceImpl implements ArticleService {

    @Value("${minio.buckek.article.name}")
    private String articleBucket;
    private static final String DEFAULT_IMAGE_URL = "https://images.unsplash.com/photo-1535982330050-f1c2fb79ff78?ixlib=rb-1.2.1&ixid=eyJhcHBfaWQiOjEyMDd9&w=1000&q=80";

    private final ArticleRepository articleRepository;
    private final UserService userService;
    private final MinioService minioService;

    @Override
    public RsArticleDto save(RqCreateArticleDto rqCreateArticleDto, MultipartFile titleImage, MultipartFile contentImage) {
        String titleImageUrl = saveImage(titleImage);
        String contentImageUrl = saveImage(contentImage);

        Article article = ArticleMapper.INSTANCE.rqCreateArticleDtoToArticle(rqCreateArticleDto);
        article.setUser(userService.findById(rqCreateArticleDto.getUserId()));
        article.setTitleImageUrl(titleImageUrl);
        article.setContentImageUrl(contentImageUrl);
        return ArticleMapper.INSTANCE.articleToRsArticleDto(articleRepository.save(article));
    }

    @Override
    public RsArticleDto update(RqUpdateArticleDto rqUpdateArticleDto, Long id) {
        Article article = findById(id);
        ArticleMapper.INSTANCE.updateArticleFromRqUpdateArticleDto(rqUpdateArticleDto, article);
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
    public Article findById(Long id) {
        return articleRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("article not found by id: " + id));
    }

    @Override
    public Long count() {
        return articleRepository.count();
    }

    private String saveImage(MultipartFile multipartFile) {

        if (multipartFile != null) {
            String imageName = "image_" + UUID.randomUUID().toString() + ".jpg";
            try {
                return minioService.uploadImage(imageName, multipartFile.getBytes(), articleBucket);
            } catch (IOException e) {
                throw new RuntimeException(e.getMessage());
            }
        }

        return DEFAULT_IMAGE_URL;
    }

}
