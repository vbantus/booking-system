package com.fablab.booking.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
public class RqCreateArticleDto {
    @NotBlank
    @Size(max = 200, message = "the max length is 200 characters")
    @ApiModelProperty(value = "title", example = "awesome title")
    private String title;
    @NotBlank
    @ApiModelProperty(value = "content", example = "cool content")
    private String content;
    @NotBlank
    @ApiModelProperty(value = "name of the category the article belongs to", example = "ADVERTISEMENT")
    private String category;
    @NotNull
    @ApiModelProperty(value = "userId", example = "1")
    private Long userId;
}
