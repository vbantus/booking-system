package com.fablab.booking.controller;

import com.fablab.booking.dto.RqCreateEventDto;
import com.fablab.booking.dto.RqUpdateEventDto;
import com.fablab.booking.dto.RsEventDto;
import com.fablab.booking.service.EventService;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import springfox.documentation.annotations.ApiIgnore;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/booking/event")
public class EventController {

    private final EventService eventService;

    @PostMapping
    public ResponseEntity<RsEventDto> save(RqCreateEventDto rqCreateEventDto,
                                           @RequestParam(value = "image", required = false) MultipartFile image) {
        return ResponseEntity.status(HttpStatus.CREATED).body(eventService.save(rqCreateEventDto, image));
    }

    @PatchMapping("/{eventId}")
    public ResponseEntity<RsEventDto> update(RqUpdateEventDto rqUpdateEventDto,
                                             @RequestParam(value = "image", required = false) MultipartFile image,
                                             @PathVariable("eventId") Long eventId) {
        return ResponseEntity.ok(eventService.update(rqUpdateEventDto, image, eventId));
    }

    @DeleteMapping("/{eventId}")
    public ResponseEntity<Void> deleteById(@PathVariable("eventId") Long eventId) {
        eventService.deleteById(eventId);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @ApiImplicitParams({
            @ApiImplicitParam(name = "page", dataType = "integer", paramType = "query",
                    value = "Results page you want to retrieve (0..N)", defaultValue = "0"),
            @ApiImplicitParam(name = "size", dataType = "integer", paramType = "query",
                    value = "Number of records per page.", defaultValue = "20")
    })
    @GetMapping
    public ResponseEntity<List<RsEventDto>> getAll(@ApiIgnore Pageable pageable) {
        return ResponseEntity.ok(eventService.getAll(pageable));
    }

    @GetMapping("/{eventId}")
    public ResponseEntity<RsEventDto> getById(@PathVariable("eventId") Long eventId) {
        return ResponseEntity.status(HttpStatus.OK).body(eventService.getById(eventId));
    }

}
