package com.fablab.booking.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
public class RqCreateCommentDto {
    @NotBlank
    @ApiModelProperty(value = "content", example = "very interesting article")
    private String content;
    @NotNull
    @ApiModelProperty(value = "articleId", example = "5")
    private Long articleId;
    @NotNull
    @ApiModelProperty(value = "userId", example = "1")
    private Long userId;
}
