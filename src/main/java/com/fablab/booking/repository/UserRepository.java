package com.fablab.booking.repository;

import com.fablab.booking.domain.BookingUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<BookingUser, Long> {
    @Query("select u from BookingUser u inner join fetch u.authorities where u.username = :username")
    Optional<BookingUser> findByUsername(@Param("username") String username);
}
