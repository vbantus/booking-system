package com.fablab.booking.mapper;

import com.fablab.booking.domain.Article;
import com.fablab.booking.dto.ArticleDto;
import org.mapstruct.*;
import org.mapstruct.factory.Mappers;

@Mapper(uses = {CommentMapper.class}, builder = @Builder(disableBuilder = true))
public interface ArticleMapper {
    ArticleMapper INSTANCE = Mappers.getMapper(ArticleMapper.class);

    @Mapping(source = "comments", target = "commentDtos")
    ArticleDto articleToArticleDto(Article article);

    @InheritInverseConfiguration
    Article articleDtoToArticle(ArticleDto articleDto);


    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createDate", ignore = true)
    @Mapping(target = "updateDate", ignore = true)
    void updateArticleFromArticleDto(ArticleDto articleDto, @MappingTarget Article article);
}
