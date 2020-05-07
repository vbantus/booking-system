package com.fablab.booking.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class RqCreateArticleDto {
    @ApiModelProperty(value = "title", example = "awesome title")
    private String title;
    @ApiModelProperty(value = "content", example = "cool content")
    private String content;
    @ApiModelProperty(value = "title", example = "1")
    private Long userId;
}
