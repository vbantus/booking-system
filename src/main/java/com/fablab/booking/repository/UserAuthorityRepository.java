package com.fablab.booking.repository;

import com.fablab.booking.domain.UserAuthority;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserAuthorityRepository extends JpaRepository<UserAuthority, Long> {
}
