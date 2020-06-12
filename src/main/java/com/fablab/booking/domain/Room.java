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
@Table(name = "rooms")
public class Room extends AbstractEntity {
    @Column(unique = true, nullable = false)
    private String name;
    private int pricePerHour;
    private String description;
    private String imageUrl;
}
