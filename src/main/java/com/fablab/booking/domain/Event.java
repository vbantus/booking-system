package com.fablab.booking.domain;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import java.time.Duration;
import java.util.Date;

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
    private String description;
    private String location;
    private int participationFee;
    private String imageUrl;
    private Date startTime;
    private Date endTime;
    @ManyToOne(
            fetch = FetchType.LAZY
    )
    @JoinColumn(name = "user_id")
    private BookingUser user;
}
