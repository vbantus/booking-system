package com.fablab.booking.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class RqUpdateCommentDto {
    @NotBlank
    @ApiModelProperty(value = "content", example = "very interesting article")
    private String content;
}
