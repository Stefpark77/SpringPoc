package com.example.spring_data.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class AdviceHandler {
    private final Logger logger = LoggerFactory.getLogger(getClass());

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<String> handleException(IllegalArgumentException ex) {
        logger.error("Illegal Argument Exception", ex);
        return new ResponseEntity<>("Invalid arguments", HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(EmptyResultDataAccessException.class)
    public ResponseEntity<String> handleException(EmptyResultDataAccessException ex) {
        logger.error("Empty Result Data Access Exception", ex);
        return new ResponseEntity<>("Data was not found for those parameters", HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(Throwable.class)
    public ResponseEntity<String> handleException(Exception ex) {
        logger.error("Exception", ex);
        return new ResponseEntity<>("Server Error", HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
