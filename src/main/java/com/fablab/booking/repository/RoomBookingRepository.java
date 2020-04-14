package com.fablab.booking.repository;

import com.fablab.booking.domain.RoomBooking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RoomBookingRepository extends JpaRepository<RoomBooking, Long> {

    //TODO modify the queries

    @Query(value = "select b from RoomBooking b where b.startBookingTime > current_timestamp and b.endBookingTime > current_timestamp ")
    List<RoomBooking> findAllPendingBookings();

    @Query(value = "select b from RoomBooking b where b.startBookingTime < current_timestamp and current_timestamp < b.endBookingTime")
    List<RoomBooking> findAllActiveBookings();

    @Query(value = "select b from RoomBooking b where b.startBookingTime < current_timestamp and  b.endBookingTime < current_timestamp")
    List<RoomBooking> findAllExpiredBookings();

    @Query(value = "select b from RoomBooking b " +
            "where b.startBookingTime > current_timestamp and b.endBookingTime > current_timestamp " +
            "and b.user.id = :userId")
    List<RoomBooking> findAllPendingBookingsByUserId(@Param("userId") Long userId);

    @Query(value = "select b from RoomBooking b " +
            "where b.startBookingTime < current_timestamp and current_timestamp < b.endBookingTime " +
            "and b.user.id = :userId")
    List<RoomBooking> findAllActiveBookingsByUserId(@Param("userId") Long userId);

    @Query(value = "select b from RoomBooking b " +
            "where b.startBookingTime < current_timestamp and  b.endBookingTime < current_timestamp " +
            "and b.user.id = :userId")
    List<RoomBooking> findAllExpiredBookingsByUserId(@Param("userId") Long userId);
}
