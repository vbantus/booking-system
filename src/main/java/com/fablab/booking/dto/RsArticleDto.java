package com.fablab.booking.dto;

import lombok.Data;

import java.util.List;

@Data
public class RsArticleDto extends AbstractResponseEntityDto {
    private String title;
    private String content;
    private Long userId;
    private String titleImageUrl;
    private String contentImageUrl;
    private List<RsCommentDto> commentDtos;
}
