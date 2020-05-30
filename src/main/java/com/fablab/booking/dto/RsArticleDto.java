package com.fablab.booking.dto;

import lombok.Data;

import java.util.List;

@Data
public class RsArticleDto extends AbstractResponseEntityDto {
    private String title;
    private String content;
    private String category;
    private Long userId;
    private String imageUrl;
    private List<RsCommentDto> commentDtos;
}
