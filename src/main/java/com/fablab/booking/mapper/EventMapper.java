package com.fablab.booking.mapper;

import com.fablab.booking.domain.Event;
import com.fablab.booking.dto.RqCreateEventDto;
import com.fablab.booking.dto.RqUpdateEventDto;
import com.fablab.booking.dto.RsEventDto;
import org.mapstruct.Builder;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

@Mapper(builder = @Builder(disableBuilder = true))
public interface EventMapper {

    EventMapper INSTANCE = Mappers.getMapper(EventMapper.class);

    Event rqCreateEventDtoToEvent(RqCreateEventDto rqCreateEventDto);

    @Mapping(source = "event.user.id", target = "userId")
    RsEventDto eventToRsEventDto(Event event);

    void updateEventFromRqUpdateEventDto(RqUpdateEventDto rqUpdateEventDto, @MappingTarget Event event);
}
