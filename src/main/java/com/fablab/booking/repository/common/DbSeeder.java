package com.fablab.booking.repository.common;

import com.fablab.booking.domain.BookingUser;
import com.fablab.booking.domain.UserAuthority;
import com.fablab.booking.domain.common.UserRole;
import com.fablab.booking.repository.UserAuthorityRepository;
import com.fablab.booking.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.Set;

@Component
@RequiredArgsConstructor
public class DbSeeder implements CommandLineRunner {

    private final UserAuthorityRepository userAuthorityRepository;
    private final UserRepository userRepository;

    @Override
    public void run(String... args) throws Exception {
        if(userAuthorityRepository.findAll().isEmpty()){
            populateDb();
        }
    }

    private void populateDb(){
        UserAuthority adminAuthority = UserAuthority.builder()
                .role(UserRole.ROLE_ADMIN)
                .build();

        UserAuthority userAuthority = UserAuthority.builder()
                .role(UserRole.ROLE_USER)
                .build();

        BookingUser simpleUser = BookingUser.builder()
                .username("user")
                .password("user")
                .email("user@gmail.com")
                .authorities(Set.of(userAuthority))
                .build();

        BookingUser adminUser = BookingUser.builder()
                .username("admin")
                .password("admin")
                .email("admin@gmail.com")
                .authorities(Set.of(adminAuthority))
                .build();

        userAuthorityRepository.saveAll(Arrays.asList(userAuthority, adminAuthority));
        userRepository.saveAll(Arrays.asList(simpleUser, adminUser));
    }
}
