package com.kaan.Blog.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(UserException.class)
    public ResponseEntity<String> handleUserException (UserException ex) {
        return ResponseEntity.badRequest().body(ex.getMessage()) ;
    }

    @ExceptionHandler(JwtException.class)
    public ResponseEntity<String> handleJwtException (JwtException ex) {
        return ResponseEntity.badRequest().body(ex.getMessage()) ;
    }

}
