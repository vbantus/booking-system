package com.fablab.booking.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@Data
@EqualsAndHashCode(callSuper = true)
public class ArticleDto extends AbstractEntityDto {
    private String title;
    private String content;
    private Long userId;
    @ApiModelProperty(readOnly = true)
    private List<CommentDto> commentDtos;
}
