package com.fablab.booking.service.impl;

import com.fablab.booking.domain.BookingUser;
import com.fablab.booking.dto.RqRegisterUserDto;
import com.fablab.booking.dto.RsUserDto;
import com.fablab.booking.mapper.UserMapper;
import com.fablab.booking.repository.UserRepository;
import com.fablab.booking.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    @Override
    public RsUserDto save(RqRegisterUserDto rqRegisterUserDto) {
        BookingUser user = UserMapper.INSTANCE.rqRegisterUserDtoToUser(rqRegisterUserDto);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return UserMapper.INSTANCE.userToRsUserDto(userRepository.save(user));
    }
}
