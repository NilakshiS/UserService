package com.stackroute.Muzix.controller;

import com.stackroute.Muzix.domain.User;
import com.stackroute.Muzix.exceptions.UserAlreadyExistsException;
import com.stackroute.Muzix.exceptions.UserNotFoundException;
import com.stackroute.Muzix.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

//Controller class that handles requests and sends a response
@RestController
@RequestMapping("api/v1")
//Swagger2 annotation for documentation
@Api(tags = {"User Controller"})
public class UserController {

    private UserService userService;

    //Autowired to inject the userService dependency
    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    //swagger2 annotation for documentation
    @ApiOperation(value = "Insert a user", response = ResponseEntity.class)
    //mapping to post request to /user
    @PostMapping("user")
    //handler to save user
    public ResponseEntity<?> saveUser(@RequestBody User user) throws UserAlreadyExistsException {
        userService.saveUser(user);
        return new ResponseEntity<>("Successfully created", HttpStatus.CREATED);
    }

    @ApiOperation(value = "Get a list of all available users", response = ResponseEntity.class)
    //mapping to get request to /user
    @GetMapping("user")
    //handler to get all user
    public ResponseEntity<?> getAllUsers() {
        return new ResponseEntity<>(userService.getAllUsers(), HttpStatus.OK);
    }

    @ApiOperation(value = "Get the user requested by id", response = ResponseEntity.class)
    //mapping to get request to /user/id
    @GetMapping("user/{id}")
    //handler to get a user by id
    public ResponseEntity<?> getUser(@PathVariable String id) {
        try {
            return new ResponseEntity<>(userService.getUserById(Integer.parseInt(id)), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.CONFLICT);
        }
    }

    @ApiOperation(value = "Update a user", response = ResponseEntity.class)
    //mapping to put request to /user
    @PutMapping("user")
    //handler to update a user
    public ResponseEntity<?> updateUser(@RequestBody User user) throws UserNotFoundException {
        userService.updateUser(user);
        return new ResponseEntity<>("Successfully updated", HttpStatus.OK);
    }

    @ApiOperation(value = "Delete the user whose id id given", response = ResponseEntity.class)
    //mapping to delete request to /user/id
    @DeleteMapping("user/{id}")
    //handler to delete a user
    public ResponseEntity<?> deleteUser(@PathVariable String id) throws UserNotFoundException {
        userService.deleteUser(Integer.parseInt(id));
        return new ResponseEntity<>("user deleted", HttpStatus.OK);
    }

    @ApiOperation(value = "Search all users by name", response = ResponseEntity.class)
    //mapping to get request to /user/search/name
    @GetMapping("user/search/{name}")
    //handler to search for a user
    public ResponseEntity<?> searchUser(@PathVariable String name) {
        return new ResponseEntity<>(userService.getUserByNameOrComments(name), HttpStatus.OK);
    }
}
