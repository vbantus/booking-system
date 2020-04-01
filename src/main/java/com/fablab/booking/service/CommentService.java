package com.fablab.booking.service;

import com.fablab.booking.domain.Comment;
import com.fablab.booking.dto.CommentDto;

public interface CommentService {
    CommentDto findDtoById(Long id);

    CommentDto save(CommentDto commentDto);

    CommentDto update(CommentDto commentDto);

    void deleteById(Long id);
}
