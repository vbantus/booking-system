package com.fablab.booking.mapper;

import com.fablab.booking.domain.BookingUser;
import com.fablab.booking.dto.RqRegisterUserDto;
import com.fablab.booking.dto.RqUpdateUserDto;
import com.fablab.booking.dto.RsUserDto;
import org.mapstruct.Builder;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

@Mapper(builder = @Builder(disableBuilder = true))
public interface UserMapper {

    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    BookingUser rqRegisterUserDtoToUser(RqRegisterUserDto rqRegisterUserDto);
    //TODO map userRoles to List<String>
    RsUserDto userToRsUserDto(BookingUser user);

    void updateUserFromRqUserUpdateDto(RqUpdateUserDto rqUpdateUserDto, @MappingTarget BookingUser user);
}
