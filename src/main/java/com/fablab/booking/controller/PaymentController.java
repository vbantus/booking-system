package com.fablab.booking.controller;

import com.fablab.booking.dto.RqPaymentDto;
import com.fablab.booking.dto.RsPaymentDto;
import com.fablab.booking.service.EventService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.Random;

//this is a dummy end-point
//business logic is not yet implemented

@RequiredArgsConstructor
@RestController
@RequestMapping("/booking/payment")
public class PaymentController {

    private final EventService eventService;

    @PostMapping
    public ResponseEntity<RsPaymentDto> performPayment(@Valid @RequestBody RqPaymentDto rqPaymentDto) {
        RsPaymentDto rsPaymentDto = RsPaymentDto.builder()
                .email("booking@gmail.com")
                .orderId(new Random().nextInt(25000))
                .eventDetails(eventService.getById(rqPaymentDto.getPaymentDetails().getEventId()))
                .build();
        return ResponseEntity.ok(rsPaymentDto);
    }
}
