package com.fablab.booking.mapper;

import com.fablab.booking.domain.Article;
import com.fablab.booking.dto.RqCreateArticleDto;
import com.fablab.booking.dto.RqUpdateArticleDto;
import com.fablab.booking.dto.RsArticleDto;
import org.mapstruct.Builder;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

@Mapper(uses = {CommentMapper.class}, builder = @Builder(disableBuilder = true))
public interface ArticleMapper {

    ArticleMapper INSTANCE = Mappers.getMapper(ArticleMapper.class);

    @Mapping(target = "category", ignore = true)
    Article rqCreateArticleDtoToArticle(RqCreateArticleDto rqCreateArticleDto);

    @Mapping(source = "category.name", target = "category")
    @Mapping(source = "comments", target = "commentDtos")
    @Mapping(source = "user.id", target = "userId")
    RsArticleDto articleToRsArticleDto(Article article);

    @Mapping(target = "category", ignore = true)
    void updateArticleFromRqUpdateArticleDto(RqUpdateArticleDto rqUpdateArticleDto, @MappingTarget Article article);
}
