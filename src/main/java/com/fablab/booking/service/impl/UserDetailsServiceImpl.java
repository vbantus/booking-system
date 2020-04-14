package com.fablab.booking.service.impl;

import com.fablab.booking.domain.BookingUser;
import com.fablab.booking.domain.UserAuthority;
import com.fablab.booking.domain.common.UserPrinciple;
import com.fablab.booking.repository.UserRepository;
import com.fablab.booking.repository.UserAuthorityRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    private final UserRepository userRepository;
    private final UserAuthorityRepository userAuthorityRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        BookingUser user = userRepository.findByUsername(username).orElseThrow(
                () -> new UsernameNotFoundException("User Not Found, username  : " + username));
        //return new User(bookingUser.getUsername(), bookingUser.getPassword(), getAuthorities(bookingUser.getUsername()));
        return UserPrinciple.create(user, getAuthorities(user.getAuthorities()));
    }

    private List<? extends GrantedAuthority> getAuthorities(Set<UserAuthority> authorities) {
        return authorities.stream()
                .map(a -> new SimpleGrantedAuthority(a.getRole().toString()))
                .collect(Collectors.toList());
    }
}
