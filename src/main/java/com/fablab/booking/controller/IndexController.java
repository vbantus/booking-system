package com.fablab.booking.controller;

import com.fablab.booking.domain.UserAuthority;
import com.fablab.booking.domain.util.UserRole;
import com.fablab.booking.repository.UserAuthorityRepository;
import com.fablab.booking.repository.BookingUserRepository;
import com.fablab.booking.service.ArticleService;
import com.fablab.booking.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.persistence.EntityManager;

@RequiredArgsConstructor
@RestController
@CrossOrigin
@RequestMapping("/booking/api/test")
public class IndexController {

    private final ArticleService articleService;
    private final CommentService commentService;
    private final BookingUserRepository bookingUserRepository;
    private final UserAuthorityRepository userAuthorityRepository;
    private final EntityManager entityManager;

    @GetMapping
    public ResponseEntity<String> hi() {
        UserAuthority userAuthority = UserAuthority.builder()
                .role(UserRole.ROLE_USER)
                .bookingUser(bookingUserRepository.findByUsername("vasea").get())
                .build();

        userAuthorityRepository.save(userAuthority);
        return ResponseEntity.status(HttpStatus.OK).body("hi there");
    }
}
