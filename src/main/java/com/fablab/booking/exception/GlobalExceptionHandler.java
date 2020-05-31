package com.fablab.booking.exception;

import org.hibernate.exception.ConstraintViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;
import java.util.stream.Collectors;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler({MethodArgumentNotValidException.class})
    public ResponseEntity<ValidationResponseDto> handleMethodArgumentNotValidException(MethodArgumentNotValidException ex) {

        List<ValidationError> errors = ex.getBindingResult().getFieldErrors().stream()
                .map(e -> ValidationError.builder()
                        .field(e.getField())
                        .message(e.getDefaultMessage())
                        .build())
                .collect(Collectors.toList());

        ValidationResponseDto validationResponse = ValidationResponseDto.builder()
                .message("Validation Failed")
                .fields(errors.size())
                .validationErrors(errors)
                .build();
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(validationResponse);
    }

    @ExceptionHandler({BindException.class})
    public ResponseEntity<ValidationResponseDto> handleBindException(BindException ex) {

        List<ValidationError> errors = ex.getFieldErrors().stream()
                .map(e -> ValidationError.builder()
                        .field(e.getField())
                        .message(e.getDefaultMessage())
                        .build())
                .collect(Collectors.toList());

        ValidationResponseDto validationResponse = ValidationResponseDto.builder()
                .message("Validation Failed")
                .fields(errors.size())
                .validationErrors(errors)
                .build();
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(validationResponse);
    }

    @ExceptionHandler({EntityNotFoundException.class})
    public ResponseEntity<String> handleNotFoundExceptions(RuntimeException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
    }

    @ExceptionHandler({TimeConflictException.class, BookingNotAllowedException.class})
    public ResponseEntity<String> handleTimeConflictExceptions(RuntimeException ex) {
        return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(ex.getMessage());
    }

    @ExceptionHandler({ConstraintViolationException.class})
    public ResponseEntity<String> handleConstraintViolationException(ConstraintViolationException ex) {
        return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(ex.getSQLException().getMessage());
    }

//    //javax.validation.ConstraintViolationException.class
//    @ExceptionHandler({javax.validation.ConstraintViolationException.class})
//    public ResponseEntity<List<String>> handleConstraintViolationException(ConstraintViolationException ex) {
//
//        List<String> errors = ex.getConstraintViolations().stream()
//                .map(e -> Iterables.getLast(e.getPropertyPath()).getName() + ": " + e.getMessage())
//                .collect(Collectors.toList());
//
//        return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(errors);
//    }
}
