package com.fablab.booking.controller;

import com.fablab.booking.dto.RqCreateCommentDto;
import com.fablab.booking.dto.RqUpdateCommentDto;
import com.fablab.booking.dto.RsCommentDto;
import com.fablab.booking.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RequiredArgsConstructor
@RestController
@RequestMapping("/booking/comments")
public class CommentController {

    private final CommentService commentService;

    @PostMapping
    public ResponseEntity<RsCommentDto> save(@Valid @RequestBody RqCreateCommentDto rqCreateCommentDto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(commentService.save(rqCreateCommentDto));
    }

    @PutMapping("/{commentId}")
    public ResponseEntity<RsCommentDto> update(@Valid @RequestBody RqUpdateCommentDto rqUpdateCommentDto,
                                               @PathVariable("commentId") Long commentId) {
        return ResponseEntity.status(HttpStatus.OK).body(commentService.update(rqUpdateCommentDto, commentId));
    }

    @DeleteMapping("/{commentId}")
    public ResponseEntity<Void> deleteById(@PathVariable("commentId") Long commentId) {
        commentService.deleteById(commentId);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

}
