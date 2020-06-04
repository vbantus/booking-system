package com.fablab.booking.repository;

import com.fablab.booking.domain.BookingUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<BookingUser, Long> {

    Optional<BookingUser> findByUsername(String username);

    List<BookingUser> findAllByCreateDateGreaterThan(Date date);
}
