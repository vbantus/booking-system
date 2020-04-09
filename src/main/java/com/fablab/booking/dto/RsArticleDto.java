package com.fablab.booking.dto;

import lombok.Data;

import java.util.List;

@Data
public class RsArticleDto extends AbstractResponseEntityDto {
    private String title;
    private String content;
    private Long userId;
    private List<RsCommentDto> commentDtos;
}
