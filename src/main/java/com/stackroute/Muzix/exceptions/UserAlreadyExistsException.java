package com.stackroute.Muzix.exceptions;

//Custom Exception Class
public class UserAlreadyExistsException extends Exception {

    private String message;
    public UserAlreadyExistsException() {
    }

    public UserAlreadyExistsException(String message) {
        super(message);
        this.message = message;
    }
}
