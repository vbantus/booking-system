package com.fablab.booking.domain;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "article_categories")
public class ArticleCategory extends AbstractEntity {
    @Column(length = 50, unique = true, nullable = false)
    private String name;
    @Column(length = 300, nullable = false)
    private String description;
}
