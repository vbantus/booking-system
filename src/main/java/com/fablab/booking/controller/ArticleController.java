package com.fablab.booking.controller;

import com.fablab.booking.dto.RqCreateArticleDto;
import com.fablab.booking.dto.RqUpdateArticleDto;
import com.fablab.booking.dto.RsArticleDto;
import com.fablab.booking.dto.RsCommentDto;
import com.fablab.booking.service.ArticleService;
import com.fablab.booking.service.CommentService;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
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
import springfox.documentation.annotations.ApiIgnore;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/booking/api/article")
public class ArticleController {

    private final ArticleService articleService;
    private final CommentService commentService;

    @GetMapping("/{articleId}")
    public ResponseEntity<RsArticleDto> getById(@PathVariable("articleId") Long articleId) {
        return ResponseEntity.status(HttpStatus.OK).body(articleService.findDtoById(articleId));
    }

    @GetMapping("/{articleId}/comments")
    public ResponseEntity<List<RsCommentDto>> getAllCommentsByArticleId(@PathVariable("articleId") Long articleId) {
        return ResponseEntity.status(HttpStatus.OK).body(commentService.findAllDtoByArticleId(articleId));
    }

    @ApiImplicitParams({
            @ApiImplicitParam(name = "page", dataType = "integer", paramType = "query",
                    value = "Results page you want to retrieve (0..N)"),
            @ApiImplicitParam(name = "size", dataType = "integer", paramType = "query",
                    value = "Number of records per page.")
    })
    @GetMapping
    public ResponseEntity<List<RsArticleDto>> getAll(@ApiIgnore Pageable pageable) {
        return ResponseEntity.ok(articleService.findAllDto(pageable));
    }

    @PostMapping
    public ResponseEntity<RsArticleDto> save(@RequestBody RqCreateArticleDto rqCreateArticleDto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(articleService.save(rqCreateArticleDto));
    }

    @PutMapping("/{articleId}")
    public ResponseEntity<RsArticleDto> update(@PathVariable("articleId") Long articleId,
                                               @RequestBody RqUpdateArticleDto rqUpdateArticleDto) {
        return ResponseEntity.status(HttpStatus.OK).body(articleService.update(rqUpdateArticleDto, articleId));
    }

    @DeleteMapping("/{articleId}")
    public ResponseEntity<Void> deleteById(@PathVariable("articleId") Long articleId) {
        articleService.deleteById(articleId);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
