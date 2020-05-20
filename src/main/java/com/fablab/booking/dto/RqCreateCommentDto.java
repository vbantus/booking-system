package com.fablab.booking.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class RqCreateCommentDto {
    private String content;
    private Long articleId;
    @ApiModelProperty(value = "userId", example = "1")
    private Long userId;
}
