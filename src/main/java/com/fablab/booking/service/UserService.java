package com.fablab.booking.service;

import com.fablab.booking.domain.BookingUser;
import com.fablab.booking.dto.RqRegisterUserDto;
import com.fablab.booking.dto.RqUpdateUserDto;
import com.fablab.booking.dto.RsUserDto;

import java.util.Date;
import java.util.List;

public interface UserService {

    RsUserDto save(RqRegisterUserDto rqRegisterUserDto);

    RsUserDto update(RqUpdateUserDto rqUpdateUserDto, Long id);

    void deleteById(Long id);

    List<RsUserDto> getAll();

    RsUserDto findByUsername(String username);

    BookingUser findById(Long id);

    List<BookingUser> findAllByCreateDateGreaterThan(Date date);
}
