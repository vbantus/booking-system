package com.fablab.booking.controller;

import com.fablab.booking.dto.RqArticleCategoryDto;
import com.fablab.booking.dto.RsArticleCategoryDto;
import com.fablab.booking.dto.RsArticleDto;
import com.fablab.booking.service.ArticleCategoryService;
import com.fablab.booking.service.ArticleService;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.annotations.ApiIgnore;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/booking/article-categories")
public class ArticleCategoryController {

    private final ArticleCategoryService articleCategoryService;
    private final ArticleService articleService;

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
    public ResponseEntity<RsArticleCategoryDto> getByName(@PathVariable("articleCategoryName") String articleCategoryName) {
        return ResponseEntity.ok(articleCategoryService.getByName(articleCategoryName));
    }

    @ApiImplicitParams({
            @ApiImplicitParam(name = "page", dataType = "integer", paramType = "query",
                    value = "Results page you want to retrieve (0..N)", defaultValue = "0"),
            @ApiImplicitParam(name = "size", dataType = "integer", paramType = "query",
                    value = "Number of records per page.", defaultValue = "20"),
            @ApiImplicitParam(name = "sort", allowMultiple = true, dataType = "string", paramType = "query",
                    value = "Sorting criteria in the format: property(,asc|desc). " +
                            "Default sort order is ascending. " +
                            "Multiple sort criteria are supported.")
    })
    @GetMapping("/{category}/articles")
    public ResponseEntity<List<RsArticleDto>> getAllArticlesByCategoryName(@PathVariable("category") String articleCategoryName,
                                                                           @ApiIgnore Pageable pageable) {
        return ResponseEntity.ok(articleService.getAllByCategoryName(articleCategoryName, pageable));
    }

    @GetMapping("/{category}/articles/count")
    public ResponseEntity<Long> getNumberOfArticlesByCategory(@PathVariable("category") String articleCategoryName) {
        return ResponseEntity.status(HttpStatus.OK).body(articleService.countArticlesByCategoryName(articleCategoryName));
    }

}
