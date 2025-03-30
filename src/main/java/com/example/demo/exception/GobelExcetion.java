package com.example.demo.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.time.LocalDateTime;
@ControllerAdvice
public class GobelExcetion {
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorDetails> ohterExceptionHandler(Exception e , WebRequest webRequest) {
        ErrorDetails errorDetais = new ErrorDetails(e.getMessage(), webRequest.getDescription(false), LocalDateTime.now());

        return new ResponseEntity<ErrorDetails>(errorDetais, HttpStatus.BAD_REQUEST);
    }
}
