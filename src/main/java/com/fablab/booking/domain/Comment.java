package com.fablab.booking.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@EqualsAndHashCode(callSuper = true, exclude = "article")
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString(exclude = "article")
@Builder
@Entity
@Table(name = "comments")
public class Comment extends AbstractEntity {
    @Column(columnDefinition="TEXT", nullable = false)
    private String content;
    @ManyToOne(
            fetch = FetchType.LAZY
    )
    @JoinColumn(name = "article_id")
    private Article article;
    @ManyToOne(
            fetch = FetchType.LAZY
    )
    @JoinColumn(name = "user_id")
    private BookingUser user;
}
