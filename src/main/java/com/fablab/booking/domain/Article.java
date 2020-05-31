package com.fablab.booking.domain;

import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "articles")
public class Article extends AbstractEntity {
    @Column(length = 200, unique = true, nullable = false)
    private String title;
    @Column(columnDefinition = "TEXT", nullable = false)
    private String content;
    private String imageUrl;
    @OneToMany(
            mappedBy = "article",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private List<Comment> comments = new ArrayList<>();
    @ManyToOne(
            fetch = FetchType.LAZY
    )
    @JoinColumn(name = "user_id")
    private BookingUser user;
    @ManyToOne(
            fetch = FetchType.LAZY
    )
    @JoinColumn(name = "article_category_id")
    private ArticleCategory category;

    public void addComment(Comment comment) {
        comments.add(comment);
        comment.setArticle(this);
    }
}
