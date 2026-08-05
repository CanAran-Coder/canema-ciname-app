package org.test.canema.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.test.canema.dto.response.ErrorResponse;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.test.canema.exception.error.ResourceNotFoundException;

import java.time.LocalDateTime;


@RestControllerAdvice
public class GlobalExceptionHandler {


    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleResourceNotFoundException(ResourceNotFoundException e) {
        ErrorResponse error = ErrorResponse.builder().status(HttpStatus.NOT_FOUND.value())
                .error(HttpStatus.NOT_FOUND.name())
                .timestamp(LocalDateTime.now())
                .message(e.getMessage())
                .build();
        return new ResponseEntity<>(error,HttpStatus.NOT_FOUND);
    }

}
