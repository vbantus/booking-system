package com.fablab.booking.domain;

import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.util.Date;

@Data
@MappedSuperclass
public class AbstractEntity {
    @Id
    @SequenceGenerator(name = "booking_generator", sequenceName = "booking_sequence", initialValue = 1, allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "booking_generator")
    private Long id;
    @CreationTimestamp
    private Date createDate;
    @UpdateTimestamp
    private Date updateDate;

}
