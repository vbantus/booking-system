package com.fablab.booking.repository;

import com.fablab.booking.domain.Article;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ArticleRepository extends JpaRepository<Article, Long> {

    List<Article> findAllByUserId(Long userId, Pageable pageable);

    List<Article> findAllByCategoryName(String categoryName, Pageable pageable);

    Long countAllByCategoryName(String categoryName);
}
