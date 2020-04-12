package com.fablab.booking.controller;

import com.fablab.booking.dto.RsArticleDto;
import com.fablab.booking.service.ArticleService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/booking/api/user")
public class UserController {

    private final ArticleService articleService;

    @GetMapping("/{userId}/articles")
    public ResponseEntity<List<RsArticleDto>> getAllArticlesByUserId(@PathVariable("userId") Long userId) {
        return ResponseEntity.status(HttpStatus.OK).body(articleService.findAllDtoByUserId(userId));
    }
}