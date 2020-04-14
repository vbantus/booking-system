package com.fablab.booking.repository;

import com.fablab.booking.domain.BookingSpace;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookingSpaceRepository extends JpaRepository<BookingSpace, Long> {
}
