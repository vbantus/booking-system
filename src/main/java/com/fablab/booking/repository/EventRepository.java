package com.fablab.booking.repository;

import com.fablab.booking.domain.Event;
import com.fablab.booking.domain.RoomBooking;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EventRepository extends JpaRepository<Event, Long> {

    List<Event> findAllByUserId(Long userId);

    @Query(value = "select e from Event e where e.startTime > current_timestamp")
    List<Event> findAllUpcomingEvents(Pageable pageable);

    @Query(value = "select e from Event e where e.endTime < current_timestamp ")
    List<Event> findAllPastEvents(Pageable pageable);
}
