package com.example.sandav.infrastructure.exception;

import com.example.sandav.infrastructure.dto.Error;
import com.fasterxml.jackson.databind.JsonMappingException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;

@RestControllerAdvice
public class StarshipControllerException {

    @ExceptionHandler(StarshipNotFoundException.class)
    public ResponseEntity<Error> handleStarshipNotFound(StarshipNotFoundException e) {
        Error error = Error.builder()
                .httpStatus(HttpStatus.NOT_FOUND)
                .localDateTime(LocalDateTime.now())
                .messge(e.getMessage())
                .build();
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
    }

    @ExceptionHandler(JsonMappingException.class)
    public ResponseEntity<Error> handleJsonMammpin(JsonMappingException e) {
        Error error = Error.builder()
                .httpStatus(HttpStatus.BAD_REQUEST)
                .messge(e.getMessage())
                .build();
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
    }
}
