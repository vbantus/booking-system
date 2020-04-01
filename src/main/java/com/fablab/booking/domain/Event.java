package com.fablab.booking.domain;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import java.time.Duration;

@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "events")
public class Event extends AbstractEntity {
    @Column(length = 30, nullable = false)
    private String title;
    @NotNull
    private String content;
    private Duration duration;
    private String imagePath;
    private int entryCost;
}
