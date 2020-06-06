package com.fablab.booking.service.impl;

import com.fablab.booking.domain.BookingUser;
import com.fablab.booking.domain.common.UserRole;
import com.fablab.booking.dto.RqRegisterUserDto;
import com.fablab.booking.dto.RqUpdateUserDto;
import com.fablab.booking.dto.RsUserDto;
import com.fablab.booking.exception.EntityNotFoundException;
import com.fablab.booking.mapper.UserMapper;
import com.fablab.booking.repository.UserRepository;
import com.fablab.booking.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    @Override
    public RsUserDto save(RqRegisterUserDto rqRegisterUserDto) {
        BookingUser user = UserMapper.INSTANCE.rqRegisterUserDtoToUser(rqRegisterUserDto);
        //TODO use mapper
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRole(UserRole.ROLE_USER);
        user.setEnabled(true);
        return UserMapper.INSTANCE.userToRsUserDto(userRepository.save(user));
    }

    @Override
    public RsUserDto update(RqUpdateUserDto rqUpdateUserDto, Long id) {
        BookingUser user = findById(id);
        UserMapper.INSTANCE.updateUserFromRqUserUpdateDto(rqUpdateUserDto, user);
        return UserMapper.INSTANCE.userToRsUserDto(userRepository.save(user));
    }

    @Override
    public void deleteById(Long id) {
        userRepository.deleteById(id);
        userRepository.deleteById(id);
    }

    @Override
    public List<RsUserDto> getAll() {
        return userRepository.findAll().stream()
                .map(UserMapper.INSTANCE::userToRsUserDto)
                .collect(Collectors.toList());
    }

    @Override
    public RsUserDto findByUsername(String username) {
        BookingUser user = userRepository.findByUsername(username)
                .orElseThrow(() -> new EntityNotFoundException("user not found by username: " + username));
        return UserMapper.INSTANCE.userToRsUserDto(user);
    }

    @Override
    public BookingUser findById(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("user not found by id: " + id));
    }

    @Override
    public List<BookingUser> findAllByCreateDateGreaterThan(Date date) {
        return userRepository.findAllByCreateDateGreaterThan(date);
    }
}
