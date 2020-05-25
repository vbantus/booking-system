package com.fablab.booking.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.awt.print.Book;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "articles")
public class Article extends AbstractEntity {
    @Column(length = 200, nullable = false)
    private String title;
    @Column(columnDefinition="TEXT", nullable = false)
    private String content;
    private String imageUrl;
    @OneToMany(
            mappedBy = "article",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private List<Comment> comments;
    @ManyToOne(
            fetch = FetchType.LAZY
    )
    @JoinColumn(name = "user_id")
    private BookingUser user;

    public void addComment(Comment comment) {
        comments.add(comment);
        comment.setArticle(this);
    }
}
