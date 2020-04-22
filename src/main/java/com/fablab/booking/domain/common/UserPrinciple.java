package com.fablab.booking.domain.common;

import com.fablab.booking.domain.BookingUser;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;

@Data
@AllArgsConstructor
@Builder
public class UserPrinciple implements UserDetails {

    private Long id;

    private String username;

    private String email;
    @JsonIgnore
    private String password;

    private boolean enabled;

    private Collection<? extends GrantedAuthority> authorities;

    public static UserPrinciple create(BookingUser user) {
        return UserPrinciple.builder()
                .id(user.getId())
                .email(user.getEmail())
                .username(user.getUsername())
                .password(user.getPassword())
                .enabled(user.getEnabled())
                .authorities(Collections.singletonList(new SimpleGrantedAuthority(user.getRole().toString())))
                .build();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }
}
