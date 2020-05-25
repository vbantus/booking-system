package com.fablab.booking.controller;

import com.fablab.booking.dto.RqCreateArticleDto;
import com.fablab.booking.dto.RqUpdateArticleDto;
import com.fablab.booking.dto.RsArticleDto;
import com.fablab.booking.service.ArticleService;
import com.fablab.booking.service.CommentService;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import springfox.documentation.annotations.ApiIgnore;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/booking/articles")
public class ArticleController {

    private final ArticleService articleService;
    private final CommentService commentService;

    @PostMapping
    public ResponseEntity<RsArticleDto> save(RqCreateArticleDto rqCreateArticleDto,
                                             @RequestParam(value = "image", required = false) MultipartFile image) {
        return ResponseEntity.status(HttpStatus.CREATED).body(articleService.save(rqCreateArticleDto, image));
    }

    @PatchMapping("/{articleId}")
    public ResponseEntity<RsArticleDto> update(RqUpdateArticleDto rqUpdateArticleDto,
                                               @RequestParam(value = "image", required = false) MultipartFile image,
                                               @PathVariable("articleId") Long articleId) {
        return ResponseEntity.status(HttpStatus.OK).body(articleService.update(rqUpdateArticleDto, image, articleId));
    }

    @DeleteMapping("/{articleId}")
    public ResponseEntity<Void> deleteById(@PathVariable("articleId") Long articleId) {
        articleService.deleteById(articleId);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @ApiImplicitParams({
            @ApiImplicitParam(name = "page", dataType = "integer", paramType = "query",
                    value = "Results page you want to retrieve (0..N)", defaultValue = "0"),
            @ApiImplicitParam(name = "size", dataType = "integer", paramType = "query",
                    value = "Number of records per page.", defaultValue = "20")
    })
    @GetMapping
    public ResponseEntity<List<RsArticleDto>> getAll(@ApiIgnore Pageable pageable) {
        return ResponseEntity.ok(articleService.getAll(pageable));
    }

    @GetMapping("/{articleId}")
    public ResponseEntity<RsArticleDto> getById(@PathVariable("articleId") Long articleId) {
        return ResponseEntity.status(HttpStatus.OK).body(articleService.getById(articleId));
    }

//    @GetMapping("/{articleId}/comments")
//    public ResponseEntity<List<RsCommentDto>> getAllCommentsByArticleId(@PathVariable("articleId") Long articleId) {
//        return ResponseEntity.status(HttpStatus.OK).body(commentService.getAllByArticleId(articleId));
//    }

    @GetMapping("/count")
    public ResponseEntity<Long> getNumberOfArticles() {
        return ResponseEntity.status(HttpStatus.OK).body(articleService.count());
    }

}
