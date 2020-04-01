package com.fablab.booking.controller;

import com.fablab.booking.dto.ArticleDto;
import com.fablab.booking.service.ArticleService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/booking/api/article")
public class ArticleComment {

    private final ArticleService articleService;

    @GetMapping("/{articleId}")
    public ResponseEntity<ArticleDto> getById(@PathVariable("articleId") Long articleId) {
        return  ResponseEntity.status(HttpStatus.OK).body(articleService.findDtoById(articleId));
    }
    @PostMapping
    public ResponseEntity<ArticleDto> save(@RequestBody ArticleDto articleDto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(articleService.save(articleDto));
    }

    @PutMapping
    public ResponseEntity<ArticleDto> update(@RequestBody ArticleDto articleDto) {
        return ResponseEntity.status(HttpStatus.OK).body(articleService.update(articleDto));
    }

    @DeleteMapping("/{articleId}")
    public ResponseEntity<Void> deleteById(@PathVariable("articleId") Long articleId){
        articleService.deleteById(articleId);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
