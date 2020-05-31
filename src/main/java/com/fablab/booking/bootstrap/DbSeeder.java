package com.fablab.booking.bootstrap;

import com.fablab.booking.domain.Article;
import com.fablab.booking.domain.ArticleCategory;
import com.fablab.booking.domain.BookingUser;
import com.fablab.booking.domain.common.UserRole;
import com.fablab.booking.repository.ArticleCategoryRepository;
import com.fablab.booking.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Component
@RequiredArgsConstructor
public class DbSeeder implements CommandLineRunner {

    private final UserRepository userRepository;
    private final ArticleCategoryRepository articleCategoryRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    @Override
    public void run(String... args) throws Exception {
        if (userRepository.findAll().isEmpty()) {
            populateDb();
        }
    }

    private void populateDb() {
        BookingUser simpleUser = BookingUser.builder()
                .username("user")
                .password(passwordEncoder.encode("user"))
                .enabled(true)
                .email("user@gmail.com")
                .role(UserRole.ROLE_USER)
                .build();

        BookingUser adminUser = BookingUser.builder()
                .username("admin")
                .password(passwordEncoder.encode("admin"))
                .enabled(true)
                .email("admin@gmail.com")
                .role(UserRole.ROLE_ADMIN)
                .build();

        ArticleCategory advertisement = ArticleCategory.builder()
                .name("ADVERTISEMENT")
                .description("advertisement")
                .build();

        ArticleCategory contenst = ArticleCategory.builder()
                .name("CONTEST")
                .description("contest")
                .build();

        userRepository.saveAll(Arrays.asList(simpleUser, adminUser));
        articleCategoryRepository.saveAll(Arrays.asList(advertisement, contenst));
    }
}
