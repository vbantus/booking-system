package com.fablab.booking.mapper;

import com.fablab.booking.domain.ArticleCategory;
import com.fablab.booking.dto.RqArticleCategoryDto;
import com.fablab.booking.dto.RsArticleCategoryDto;
import org.mapstruct.Builder;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

@Mapper(builder = @Builder(disableBuilder = true))
public interface ArticleCategoryMapper {

    ArticleCategoryMapper INSTANCE = Mappers.getMapper(ArticleCategoryMapper.class);

    ArticleCategory rqCreateArticleCategoryDtoToArticleCategory(RqArticleCategoryDto rqArticleCategoryDto);

    RsArticleCategoryDto articleCategoryToRsArticleCategoryDto(ArticleCategory articleCategory);

    void updateArticleCategoryFromRqArticleCategoryDto(RqArticleCategoryDto rqArticleCategoryDto,
                                                       @MappingTarget ArticleCategory articleCategory);

}
