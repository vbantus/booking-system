package com.fablab.booking.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class RqArticleCategoryDto {
    @ApiModelProperty(value = "name", example = "Category 1")
    private String name;
    @ApiModelProperty(value = "description", example = "description of category 1")
    private String description;
}
