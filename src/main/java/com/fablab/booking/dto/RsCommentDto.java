package com.fablab.booking.dto;

import lombok.Data;

@Data
public class RsCommentDto extends AbstractResponseEntityDto {
    private String content;
    private Long articleId;
    private Long userId;
}
