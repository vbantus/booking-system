package com.fablab.booking.service;

import com.fablab.booking.dto.RqCreateCommentDto;
import com.fablab.booking.dto.RqUpdateCommentDto;
import com.fablab.booking.dto.RsCommentDto;

public interface CommentService {
    RsCommentDto findDtoById(Long id);

    RsCommentDto save(RqCreateCommentDto rqCreateCommentDto);

    RsCommentDto update(RqUpdateCommentDto rqUpdateCommentDto, Long id);

    void deleteById(Long id);
}
