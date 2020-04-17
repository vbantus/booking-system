package com.fablab.booking.service.impl;

import com.fablab.booking.domain.Article;
import com.fablab.booking.dto.RsArticleDto;
import com.fablab.booking.repository.ArticleRepository;
import com.fablab.booking.service.UserService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ArticleServiceImplTest {

    @Mock
    private ArticleRepository articleRepository;

    @Mock
    private UserService userService;

    @InjectMocks
    private ArticleServiceImpl articleService;

    @Test
    void testGetAll() {
        PageRequest pageRequest = PageRequest.of(0, 10);
        Article article = getArticle();

        when(articleRepository.findAll(pageRequest)).thenReturn(new PageImpl<>(Collections.singletonList(article)));

        List<RsArticleDto> articleDtoList = articleService.getAll(pageRequest);

        verify(articleRepository).findAll(pageRequest);
        assertThat(articleDtoList.size()).isEqualTo(1);
        assertThat(articleDtoList.get(0).getTitle()).isEqualTo(article.getTitle());
        assertThat(articleDtoList.get(0).getContent()).isEqualTo(article.getContent());
    }

    @Test
    void testGetById() {
        Article article = getArticle();
        article.setId(1l);

        when(articleRepository.findById(article.getId())).thenReturn(Optional.of(article));

        RsArticleDto rsArticleDto = articleService.getById(article.getId());

        verify(articleRepository).findById(article.getId());
        assertThat(rsArticleDto.getId()).isEqualTo(article.getId());
        assertThat(rsArticleDto.getTitle()).isEqualTo(article.getTitle());
        assertThat(rsArticleDto.getContent()).isEqualTo(article.getContent());
    }


    private Article getArticle() {
        return Article.builder()
                .title("awesome title")
                .content("content")
                .build();
    }
}
