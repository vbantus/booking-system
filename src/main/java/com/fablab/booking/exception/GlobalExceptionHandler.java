package com.fablab.booking.exception;

import com.google.common.collect.Iterables;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.validation.ConstraintViolationException;
import java.util.List;
import java.util.stream.Collectors;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler({EntityNotFoundException.class})
    public ResponseEntity<String> handleNotFoundExceptions(RuntimeException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
    }

    @ExceptionHandler({TimeConflictException.class, BookingNotAllowedException.class})
    public ResponseEntity<String> handleTimeConflictExceptions(RuntimeException ex) {
        return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(ex.getMessage());
    }

    @ExceptionHandler({MethodArgumentNotValidException.class})
    public ResponseEntity<List<String>> handleMethodArgumentNotValidException(MethodArgumentNotValidException ex) {
        List<String> errors = ex.getBindingResult().getFieldErrors().stream()
                .map(e -> e.getField() + ": " + e.getDefaultMessage())
                .collect(Collectors.toList());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errors);
    }

    @ExceptionHandler({BindException.class})
    public ResponseEntity<List<String>> handleBindException(BindException ex) {
        List<String> errors = ex.getFieldErrors().stream()
                .map(e -> e.getField() + ": " + e.getDefaultMessage())
                .collect(Collectors.toList());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errors);
    }

    @ExceptionHandler({ConstraintViolationException.class})
    public ResponseEntity<List<String>> handleConstraintViolationException(ConstraintViolationException ex) {

        List<String> errors = ex.getConstraintViolations().stream()
                .map(e -> Iterables.getLast(e.getPropertyPath()).getName() + ": " + e.getMessage())
                .collect(Collectors.toList());

        return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(errors);
    }
}
