package com.stackroute.Muzix.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.stackroute.Muzix.domain.User;
import com.stackroute.Muzix.exceptions.UserAlreadyExistsException;
import com.stackroute.Muzix.exceptions.UserNotFoundException;
import com.stackroute.Muzix.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.util.List;

//service class contains the business logic
@Service
public class UserServiceImpl implements UserService {

    //UserRepository object to perform database
    private UserRepository userRepository;

    //Autowired constructor to inject dependency
    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    //method to save user
    @Override
    public User saveUser(User user) throws UserAlreadyExistsException {
        //check if user already exists
        if (userRepository.existsById(user.getUserId())) {
            //throw custom exception
            throw new UserAlreadyExistsException("User Already Exists!");
        }
        //otherwise try to save user
        User savedUser = userRepository.save(user);
        //if new user was not created throw custom exception
        if (savedUser == null) {
            throw new UserAlreadyExistsException("User Already Exists!");
        }
        //return the user that was inserted
        return savedUser;
    }

    //method to update an existing user
    @Override
    public User updateUser(User user) throws UserNotFoundException {
        //check if user does not exist
        if (!userRepository.existsById(user.getUserId())) {
            //throw custom exception
            throw new UserNotFoundException("User Not Found!");
        }
        //otherwise update the user
        return userRepository.save(user);
    }

    //method to delete a User
    @Override
    public void deleteUser(int id)throws UserNotFoundException {
        //check if User exists
        if (!userRepository.existsById(id)) {
            //throw custom exception
            throw new UserNotFoundException("User Not Found!");
        }
        //otherwise delete the User
        userRepository.deleteById(id);
    }

    //method to get all Users in database
    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    //method to get a single User by id
    @Override
    public User getUserById(int id) throws UserNotFoundException {
        //check if User exists
        if (!userRepository.existsById(id)) {
            //throw custom exception
            throw new UserNotFoundException("User Not Found!");
        }
        //otherwise get the User
        return userRepository.findById(id).orElse(null);
    }

    //method to search for User by name or comments
    @Override
    public List<User> getUserByNameOrComments(String name) {
        return userRepository.findByUserCommentsContainingIgnoreCaseOrUserNameContainingIgnoreCase(name,name);
        //return UserRepository.findAll();
    }

    //method to get Users from api and save to database
    @Override
    public void saveUsersFromApi(){
        //RestTemplate gets response from an api
        RestTemplate restTemplate = new RestTemplate();
        //url of Last.fm api
        String fooResourceUrl = "http://ws.audioscrobbler.com/2.0/?method=chart.gettopUsers&api_key=081010bfa07bd3c5b56ee809b476993a&format=json";
        //store response in a ResponseEntity
        ResponseEntity<String> response = restTemplate.getForEntity(fooResourceUrl, String.class);

        //Object Mapper to access the JSON from the response entity
        ObjectMapper mapper = new ObjectMapper();
        JsonNode root = null;
        try {
            //read the response body to get JSON object
            root = mapper.readTree(response.getBody());
            //Store the JSON array in the object to a variable
            ArrayNode arrayNode = (ArrayNode) root.path("Users").path("User");

            //iterate the JSON array
            for (int i = 0; i < arrayNode.size(); i++) {
                //get a new User object and fill it with data using setters
                User user = new User();
                user.setUserId(i+4);  //added 4 because 3 Users already added to database in the WebConfiguration.java
                user.setUserName(arrayNode.get(i).path("name").asText());
                user.setUserComments(arrayNode.get(i).path("artist").path("name").asText());
                //save the user to database
                userRepository.save(user);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
