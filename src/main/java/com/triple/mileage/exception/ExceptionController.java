package com.triple.mileage.exception;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.client.HttpClientErrorException;

@ControllerAdvice
public class ExceptionController {

    @ExceptionHandler(NullPointerException.class)
    public ResponseEntity handleNullPointerException() {
        return new ResponseEntity(HttpStatus.FORBIDDEN);
    }

    @ExceptionHandler(JsonProcessingException.class)
    public ResponseEntity handleJsonProcessingException() {
        return new ResponseEntity(HttpStatus.BAD_REQUEST);
    }


    @ExceptionHandler(HttpClientErrorException.class)
    public ResponseEntity handleHttpClientErrorException(HttpClientErrorException e) {
        return new ResponseEntity(e.getStatusCode());
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity handleUnknownException() {
        return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
