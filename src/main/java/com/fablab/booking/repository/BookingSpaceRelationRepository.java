package com.fablab.booking.repository;

import com.fablab.booking.domain.BookingSpaceRelation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookingSpaceRelationRepository extends JpaRepository<BookingSpaceRelation, Long> {

    //TODO modify the queries

    @Query(value = "select b from BookingSpaceRelation b where b.startBookingTime > current_timestamp and b.endBookingTime > current_timestamp ")
    List<BookingSpaceRelation> findAllPendingBookings();

    @Query(value = "select b from BookingSpaceRelation b where b.startBookingTime < current_timestamp and current_timestamp < b.endBookingTime")
    List<BookingSpaceRelation> findAllActiveBookings();

    @Query(value = "select b from BookingSpaceRelation b where b.startBookingTime < current_timestamp and  b.endBookingTime < current_timestamp")
    List<BookingSpaceRelation> findAllExpiredBookings();

    @Query(value = "select b from BookingSpaceRelation b " +
            "where b.startBookingTime > current_timestamp and b.endBookingTime > current_timestamp " +
            "and b.user.id = :userId")
    List<BookingSpaceRelation> findAllPendingBookingsByUserId(@Param("userId") Long userId);

    @Query(value = "select b from BookingSpaceRelation b " +
            "where b.startBookingTime < current_timestamp and current_timestamp < b.endBookingTime " +
            "and b.user.id = :userId")
    List<BookingSpaceRelation> findAllActiveBookingsByUserId(@Param("userId") Long userId);

    @Query(value = "select b from BookingSpaceRelation b " +
            "where b.startBookingTime < current_timestamp and  b.endBookingTime < current_timestamp " +
            "and b.user.id = :userId")
    List<BookingSpaceRelation> findAllExpiredBookingsByUserId(@Param("userId") Long userId);
}
