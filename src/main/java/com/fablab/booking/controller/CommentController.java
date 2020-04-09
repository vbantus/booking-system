package com.fablab.booking.controller;

import com.fablab.booking.dto.RqCreateCommentDto;
import com.fablab.booking.dto.RqUpdateCommentDto;
import com.fablab.booking.dto.RsCommentDto;
import com.fablab.booking.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/booking/api/comment")
public class CommentController {

    private final CommentService commentService;

    @GetMapping("/{commentId}")
    public ResponseEntity<RsCommentDto> getById(@PathVariable("commentId") Long commentId) {
        return ResponseEntity.status(HttpStatus.OK).body(commentService.findDtoById(commentId));
    }

    @PostMapping
    public ResponseEntity<RsCommentDto> save(@RequestBody RqCreateCommentDto rqCreateCommentDto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(commentService.save(rqCreateCommentDto));
    }

    @PutMapping("/{commentId}")
    public ResponseEntity<RsCommentDto> update(@PathVariable("commentId") Long commentId,
                                               @RequestBody RqUpdateCommentDto rqUpdateCommentDto) {
        return ResponseEntity.status(HttpStatus.OK).body(commentService.update(rqUpdateCommentDto, commentId));
    }

    @DeleteMapping("/{commentId}")
    public ResponseEntity<Void> deleteById(@PathVariable("commentId") Long commentId) {
        commentService.deleteById(commentId);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

}
