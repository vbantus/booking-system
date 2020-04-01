package com.fablab.booking.controller;

import com.fablab.booking.dto.CommentDto;
import com.fablab.booking.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/booking/api/comment")
public class CommentController {

    private final CommentService commentService;

    @GetMapping("/{commentId}")
    public ResponseEntity<CommentDto> getById(@PathVariable("commentId") Long commentId) {
        return ResponseEntity.status(HttpStatus.OK).body(commentService.findDtoById(commentId));
    }

    @PostMapping
    public ResponseEntity<CommentDto> save(@RequestBody CommentDto commentDto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(commentService.save(commentDto));
    }

    @PutMapping
    public ResponseEntity<CommentDto> update(@RequestBody CommentDto commentDto) {
        return ResponseEntity.status(HttpStatus.OK).body(commentService.update(commentDto));
    }

    @DeleteMapping("/{commentId}")
    public ResponseEntity<Void> deleteById(@PathVariable("commentId") Long commentId){
        commentService.deleteById(commentId);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

}
