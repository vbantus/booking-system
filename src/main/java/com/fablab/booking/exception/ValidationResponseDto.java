package com.fablab.booking.exception;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Builder
@Data
public class ValidationResponseDto {
    private String message;
    private int fields;
    private List<ValidationError> validationErrors;
}
