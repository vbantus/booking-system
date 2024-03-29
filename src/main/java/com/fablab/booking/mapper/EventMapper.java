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

import static com.fablab.booking.service.utils.TimeUtils.DATE_PATTERN;

@Mapper(builder = @Builder(disableBuilder = true))
public interface EventMapper {

    EventMapper INSTANCE = Mappers.getMapper(EventMapper.class);

    @Mapping(source = "startTime", target = "startTime", dateFormat = DATE_PATTERN)
    @Mapping(source = "endTime", target = "endTime", dateFormat = DATE_PATTERN)
    Event rqCreateEventDtoToEvent(RqCreateEventDto rqCreateEventDto);

    @Mapping(source = "event.user.id", target = "userId")
    RsEventDto eventToRsEventDto(Event event);

    @Mapping(source = "startTime", target = "startTime", dateFormat = DATE_PATTERN)
    @Mapping(source = "endTime", target = "endTime", dateFormat = DATE_PATTERN)
    void updateEventFromRqUpdateEventDto(RqUpdateEventDto rqUpdateEventDto, @MappingTarget Event event);
}
