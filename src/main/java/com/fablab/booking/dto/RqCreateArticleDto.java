package com.fablab.booking.dto;

import lombok.Data;

@Data
public class RqCreateArticleDto {
    private String title;
    private String content;
    private Long userId;
}
