package com.cloudheaven.booking.exceptions;

public class UserAlreadyExistsException extends RuntimeException{

    public UserAlreadyExistsException(String email){
        super("A user with the email " + email + " already exists.");
    }

}
