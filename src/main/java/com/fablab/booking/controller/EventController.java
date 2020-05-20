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
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import springfox.documentation.annotations.ApiIgnore;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/booking/event")
public class EventController {

    private final EventService eventService;

    @PostMapping
    public ResponseEntity<RsEventDto> save(@RequestParam(value = "image", required = false) MultipartFile image,
                                           RqCreateEventDto rqCreateEventDto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(eventService.save(image, rqCreateEventDto));
    }

    @PutMapping("/{eventId}")
    public ResponseEntity<RsEventDto> update(@PathVariable("eventId") Long eventId,
                                             @RequestParam(value = "image", required = false) MultipartFile image,
                                             RqUpdateEventDto rqUpdateEventDto) {
        return ResponseEntity.ok(eventService.update(eventId, image, rqUpdateEventDto));
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
