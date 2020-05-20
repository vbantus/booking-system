package com.fablab.booking.mapper;

import com.fablab.booking.domain.Comment;
import com.fablab.booking.dto.RqCreateCommentDto;
import com.fablab.booking.dto.RqUpdateCommentDto;
import com.fablab.booking.dto.RsCommentDto;
import org.mapstruct.Builder;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

@Mapper(builder = @Builder(disableBuilder = true))
public interface CommentMapper {

    CommentMapper INSTANCE = Mappers.getMapper(CommentMapper.class);

    Comment rqCreateCommentDtoToComment(RqCreateCommentDto rqCreateCommentDto);

    Comment rqUpdateCommentDtoToComment(RqUpdateCommentDto rqUpdateCommentDto);

    @Mapping(source = "article.id", target = "articleId")
    @Mapping(source = "user.username", target = "postedBy")
    RsCommentDto commentToRsCommentDto(Comment comment);

    void updateCommentFromRqUpdateCommentDto(RqUpdateCommentDto rqUpdateCommentDto, @MappingTarget Comment comment);
}
