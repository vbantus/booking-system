package com.fablab.booking.domain;

import com.fablab.booking.domain.common.BookingStatus;
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

@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "rooms_booking")
public class RoomBooking extends AbstractEntity{
    private Date startBookingTime;
    private Date endBookingTime;
    @Enumerated(EnumType.STRING)
    private BookingStatus status;
    @ManyToOne(
            fetch = FetchType.LAZY
    )
    @JoinColumn(name = "room_id")
    private Room room;
    @ManyToOne(
            fetch = FetchType.LAZY
    )
    @JoinColumn(name = "user_id")
    private BookingUser user;
}
