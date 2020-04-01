package com.fablab.booking.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class CommentDto extends AbstractEntityDto {
    private String content;
    private Long articleId;
    private Long userId;
}
