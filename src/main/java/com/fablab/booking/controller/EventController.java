package com.fablab.booking.controller;

import com.fablab.booking.dto.RqCreateEventDto;
import com.fablab.booking.dto.RqUpdateEventDto;
import com.fablab.booking.dto.RsEventDto;
import com.fablab.booking.service.EventService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/booking/event")
public class EventController {

    private final EventService eventService;

    @PostMapping
    public ResponseEntity<RsEventDto> save(@RequestBody RqCreateEventDto rqCreateEventDto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(eventService.save(rqCreateEventDto));
    }

    @PutMapping("/{eventId}")
    public ResponseEntity<RsEventDto> update(@PathVariable("eventId") Long eventId,
                                             @RequestBody RqUpdateEventDto rqUpdateEventDto) {
        return ResponseEntity.ok(eventService.update(rqUpdateEventDto, eventId));
    }

    @DeleteMapping("/{eventId}")
    public ResponseEntity<Void> deleteById(@PathVariable("eventId") Long eventId) {
        eventService.deleteById(eventId);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @GetMapping
    public ResponseEntity<List<RsEventDto>> getAll() {
        return ResponseEntity.ok(eventService.getAll());
    }

}
