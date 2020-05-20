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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import springfox.documentation.annotations.ApiIgnore;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/booking/article")
public class ArticleController {

    private final ArticleService articleService;
    private final CommentService commentService;

    @PostMapping
    public ResponseEntity<RsArticleDto> save(@RequestParam(value = "image", required = false) MultipartFile image,
                                             RqCreateArticleDto rqCreateArticleDto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(articleService.save(image, rqCreateArticleDto));
    }

    @PutMapping("/{articleId}")
    public ResponseEntity<RsArticleDto> update(@PathVariable("articleId") Long articleId,
                                               @RequestParam(value = "image", required = false) MultipartFile image,
                                               RqUpdateArticleDto rqUpdateArticleDto) {
        return ResponseEntity.status(HttpStatus.OK).body(articleService.update(articleId, image, rqUpdateArticleDto));
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
