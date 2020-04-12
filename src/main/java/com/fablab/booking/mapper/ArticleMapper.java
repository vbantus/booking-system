package com.fablab.booking.mapper;

import com.fablab.booking.domain.Article;
import com.fablab.booking.dto.RqCreateArticleDto;
import com.fablab.booking.dto.RqUpdateArticleDto;
import com.fablab.booking.dto.RsArticleDto;
import org.mapstruct.*;
import org.mapstruct.factory.Mappers;

@Mapper(uses = {CommentMapper.class}, builder = @Builder(disableBuilder = true))
public interface ArticleMapper {

    ArticleMapper INSTANCE = Mappers.getMapper(ArticleMapper.class);

    Article rqCreateArticleDtoToArticle(RqCreateArticleDto rqCreateArticleDto);

    Article rqUpdateArticleDtoToArticle(RqUpdateArticleDto rqUpdateArticleDto);

    @Mapping(source = "comments", target = "commentDtos")
    @Mapping(source = "user.id", target = "userId")
    RsArticleDto articleToRsArticleDto(Article article);

    void updateArticleFromRqUpdateArticleDto(RqUpdateArticleDto rqUpdateArticleDto, @MappingTarget Article article);
}
