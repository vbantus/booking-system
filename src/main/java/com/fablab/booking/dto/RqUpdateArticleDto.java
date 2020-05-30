package com.fablab.booking.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class RqUpdateArticleDto {
    @ApiModelProperty(value = "title", example = "awesome title")
    private String title;
    @ApiModelProperty(value = "content", example = "cool content")
    private String content;
    @ApiModelProperty(value = "name of the category the article belongs to", example = "ADVERTISEMENT")
    private String category;
}
