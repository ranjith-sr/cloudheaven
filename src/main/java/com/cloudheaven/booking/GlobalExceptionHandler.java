package com.cloudheaven.booking;

import com.cloudheaven.booking.exceptions.ResourceNotFoundException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import java.util.HashMap;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<?> resourceNotFoundException(ResourceNotFoundException resourceNotFoundException){
        HashMap<String,String> exceptions = new HashMap<>();
        exceptions.put("Reason",resourceNotFoundException.getMessage());
        return new ResponseEntity<>(exceptions , HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<?> dataIntegrityViolationException(DataIntegrityViolationException dataIntegrityViolationException){
        HashMap<String,String> exceptions = new HashMap<>();
        exceptions.put("Error" , "Data Integrity");
        return new ResponseEntity<>(exceptions , HttpStatus.BAD_REQUEST);
    }

}
