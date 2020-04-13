package com.fablab.booking.repository;

import com.fablab.booking.domain.BookingSpaceRelation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookingSpaceRelationRepository extends JpaRepository<BookingSpaceRelation, Long> {
}
