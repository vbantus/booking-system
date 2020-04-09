package com.fablab.booking.dto;

import lombok.Data;

@Data
public class RqCreateCommentDto {
    private String content;
    private Long articleId;
    private Long userId;
}
