package com.cloudheaven.booking;

import com.cloudheaven.booking.exceptions.ResourceNotFoundException;
import com.cloudheaven.booking.exceptions.UserAlreadyExistsException;
import com.cloudheaven.booking.util.Violation;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import java.net.URI;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ProblemDetail resourceNotFoundException(ResourceNotFoundException resourceNotFoundException){
        ProblemDetail problemDetail = ProblemDetail.forStatus(HttpStatus.NOT_FOUND);
        problemDetail.setTitle("Resource Not Found");
        problemDetail.setType(URI.create("https://cloud-heaven.com/problems/resource-not-found"));
        problemDetail.setDetail(resourceNotFoundException.getMessage());
        return problemDetail;
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<?> dataIntegrityViolationException(DataIntegrityViolationException dataIntegrityViolationException){
        HashMap<String,String> exceptions = new HashMap<>();
        exceptions.put("Error" , "Data Integrity");
        return new ResponseEntity<>(exceptions , HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ProblemDetail methodArgumentNotValidException(MethodArgumentNotValidException methodArgumentNotValidException){
        ProblemDetail problemDetail = ProblemDetail.forStatus(HttpStatus.BAD_REQUEST);
        problemDetail.setTitle("Invalid Credentials");
        problemDetail.setDetail("There are some validation errors");
        problemDetail.setType(URI.create("https://cloud-heaven.com/problems/invalid-credentails"));
        List<Violation> violations = new ArrayList<>();
        for(FieldError fieldError : methodArgumentNotValidException.getFieldErrors()){
            violations.add(new Violation(fieldError.getField() , fieldError.getDefaultMessage()));
        }
        problemDetail.setProperty("violations" , violations);
        return problemDetail;
    }

    @ExceptionHandler(UserAlreadyExistsException.class)
    public ProblemDetail userAlreadyExistsException(UserAlreadyExistsException alreadyExistsException){
        ProblemDetail problemDetail = ProblemDetail.forStatus(HttpStatus.CONFLICT);
        problemDetail.setDetail(alreadyExistsException.getMessage());
        problemDetail.setTitle("Email already exists");
        problemDetail.setType(URI.create("https://cloud-heaven.com/problems/user-already-exists"));
        return problemDetail;
    }

}
