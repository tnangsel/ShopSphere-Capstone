package com.cogent.main.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;


@ControllerAdvice
public class GlobalExceptionHandler {
	
//	@ExceptionHandler(ExpiredJwtException.class)
//    public ResponseEntity<String> handleExpiredJwtException(ExpiredJwtException ex) {
//        String errorMessage = "JWT token has expired. Please obtain a new token.";
//        return new ResponseEntity<>(errorMessage, HttpStatus.UNAUTHORIZED);
//    }
//
//    @ExceptionHandler(JwtException.class)
//    public ResponseEntity<String> handleJwtException(JwtException ex) {
//        String errorMessage = "JWT token is invalid or expired. Please obtain a new token.";
//        return new ResponseEntity<>(errorMessage, HttpStatus.FORBIDDEN);
//    }

    // Exception handler for NullPointerException
    @ExceptionHandler(NullPointerException.class)
    public ResponseEntity<String> handleNullPointerException() {
        String errorMessage = "NullPointerException occurred";
        return new ResponseEntity<>(errorMessage, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    // Exception handler for 500 Internal Server Error
    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleInternalServerError() {
        String errorMessage = "An internal server error occurred";
        return new ResponseEntity<>(errorMessage, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    // Exception handler for UserIdNotFoundException
    @ExceptionHandler(UserIdNotFoundException.class)
    public ResponseEntity<String> handleUserIdNotFoundException() {
        String errorMessage = "User ID not found";
        return new ResponseEntity<>(errorMessage, HttpStatus.NOT_FOUND);
    }

//    @ExceptionHandler(FeignException.NotFound.class)
//    public ResponseEntity<String> handleFeignNotFoundException(FeignException.NotFound ex) {
//        String errorMessage = "Product with title already exist:" + ex.getMessage();
//        return new ResponseEntity<>(errorMessage, HttpStatus.NOT_FOUND);
//    }
    
    
    
}

