package com.fablab.booking.domain;

import com.fablab.booking.domain.util.BookingStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.util.Date;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "booking_space_relations")
public class BookingSpaceRelation extends AbstractEntity{
    private Date startBookingTime;
    private Date endBookingTime;
    @Enumerated(EnumType.STRING)
    private BookingStatus status;
    @ManyToOne(
            fetch = FetchType.LAZY
    )
    @JoinColumn(name = "booking_space_id")
    private BookingSpace bookingSpace;
    @ManyToOne(
            fetch = FetchType.LAZY
    )
    @JoinColumn(name = "user_id")
    private BookingUser user;
}
