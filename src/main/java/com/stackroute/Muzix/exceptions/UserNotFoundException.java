package com.stackroute.Muzix.exceptions;

//Custom Exception Class
public class UserNotFoundException extends Exception {
    String message;

    public UserNotFoundException() {
    }

    public UserNotFoundException(String message) {
        super(message);
        this.message = message;
    }
}
