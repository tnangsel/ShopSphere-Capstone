package com.cogent.main.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ProductExceptionController {

	
  @ExceptionHandler(ProductNotFoundException.class)
  public ResponseEntity<String> handleProductIdNotFoundException(ProductNotFoundException ex) {
      String errorMessage = ex.getMessage();
      return new ResponseEntity<String>(errorMessage, HttpStatus.NOT_FOUND);
  }
  
  
	 	
}
