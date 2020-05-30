package com.fablab.booking.controller;

import com.fablab.booking.dto.RqArticleCategoryDto;
import com.fablab.booking.dto.RsArticleCategoryDto;
import com.fablab.booking.service.ArticleCategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/booking/article-categories")
public class ArticleCategoryController {

    private final ArticleCategoryService articleCategoryService;

    @PostMapping
    public ResponseEntity<RsArticleCategoryDto> save(@RequestBody RqArticleCategoryDto rqArticleCategoryDto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(articleCategoryService.save(rqArticleCategoryDto));
    }

    @PatchMapping("/{articleCategoryId}")
    public ResponseEntity<RsArticleCategoryDto> update(@RequestBody RqArticleCategoryDto rqArticleCategoryDto,
                                                       @PathVariable("articleCategoryId") Long articleCategoryId) {
        return ResponseEntity.status(HttpStatus.OK).body(articleCategoryService.update(rqArticleCategoryDto, articleCategoryId));
    }

    @DeleteMapping("/{articleCategoryId}")
    public ResponseEntity<Void> deleteById(@PathVariable("articleCategoryId") Long articleCategoryId) {
        articleCategoryService.deleteById(articleCategoryId);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @GetMapping
    public ResponseEntity<List<RsArticleCategoryDto>> getAll() {
        return ResponseEntity.ok(articleCategoryService.getAll());
    }

    @GetMapping("/{articleCategoryName}")
    public ResponseEntity<RsArticleCategoryDto> getByName(@PathVariable("articleCategoryName") String  articleCategoryName) {
        return ResponseEntity.ok(articleCategoryService.getByName(articleCategoryName));
    }

}
