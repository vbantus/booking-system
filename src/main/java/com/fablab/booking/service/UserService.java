package com.fablab.booking.service;

import com.fablab.booking.domain.BookingUser;
import com.fablab.booking.dto.RqRegisterUserDto;
import com.fablab.booking.dto.RsUserDto;

public interface UserService {

    RsUserDto save(RqRegisterUserDto rqRegisterUserDto);

    BookingUser findById(Long id);

    BookingUser findByUsername(String username);
}
