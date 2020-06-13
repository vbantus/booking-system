package com.fablab.booking.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class RsPaymentDto {
    private String email;
    private Long orderId;
    private RsEventDto eventDetails;
}
