package com.stackroute.Muzix.controller;

import com.stackroute.Muzix.exceptions.UserAlreadyExistsException;
import com.stackroute.Muzix.exceptions.UserNotFoundException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

//ControllerAdvice class to handle exceptions globally
@ControllerAdvice("com.stackroute.Muzix.controller")
public class UserControllerAdvice extends ResponseEntityExceptionHandler {

    //Exception Handler for UserNotFoundException
    @ExceptionHandler(value = {UserNotFoundException.class})
    protected ResponseEntity<Object> handleNotFoundConflict(Exception ex, WebRequest request) {

        String bodyOfResponse = "User not found!";
        return handleExceptionInternal(ex, bodyOfResponse,new HttpHeaders(), HttpStatus.CONFLICT, request);
    }

    //Exception Handler for UserAlreadyExistsException
    @ExceptionHandler(value = {UserAlreadyExistsException.class})
    protected ResponseEntity<Object> handleAlreadyExistsConflict(Exception ex, WebRequest request) {

        String bodyOfResponse = "User already exists!";
        return handleExceptionInternal(ex, bodyOfResponse,new HttpHeaders(), HttpStatus.CONFLICT, request);
    }
}


