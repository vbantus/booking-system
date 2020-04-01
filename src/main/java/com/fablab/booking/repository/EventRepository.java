package com.fablab.booking.repository;

import com.fablab.booking.domain.Event;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Repository
public interface EventRepository extends JpaRepository<Event, Long> {
}
