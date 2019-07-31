package com.stackroute.Muzix.service;

import com.stackroute.Muzix.domain.User;
import com.stackroute.Muzix.exceptions.UserAlreadyExistsException;
import com.stackroute.Muzix.exceptions.UserNotFoundException;

import java.util.List;

//interface for the service class
public interface UserService {

    //method to save a user in database
    User saveUser(User user) throws UserAlreadyExistsException;

    //method to update a user already saved in database
    User updateUser(User user) throws UserNotFoundException;

    //method to delete a User
    void deleteUser(int id) throws UserNotFoundException;

    //method to get Users from last.fm api and save in database
    void saveUsersFromApi();

    //method to get all Users saved in database
    List<User> getAllUsers();

    //method to get a User by id
    User getUserById(int id) throws UserNotFoundException;

    //method to get a User by name or comments
    List<User> getUserByNameOrComments(String name);
}

