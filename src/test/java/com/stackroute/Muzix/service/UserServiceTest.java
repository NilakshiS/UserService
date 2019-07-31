package com.stackroute.Muzix.service;

import com.stackroute.Muzix.domain.User;
import com.stackroute.Muzix.exceptions.UserAlreadyExistsException;
import com.stackroute.Muzix.exceptions.UserNotFoundException;
import com.stackroute.Muzix.repository.UserRepository;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class UserServiceTest {

    User user;

    //Create a mock for UserRepository
    @Mock
    UserRepository userRepository;

    //Inject the mocks as dependencies into UserServiceImpl
    @InjectMocks
    UserServiceImpl userService;
    List<User> list= null;


    @Before
    public void setUp(){
        //Initialising the mock object
        MockitoAnnotations.initMocks(this);
        user = new User();
        user.setUserName("Love Maze");
        user.setUserComments("BTS");
        list = new ArrayList<>();
        list.add(user);
    }

    @Test
    public void saveUserTestSuccess() throws UserAlreadyExistsException {

        when(userRepository.save(any())).thenReturn(user);
        User savedUser = userService.saveUser(user);
        Assert.assertEquals(user, savedUser);

        //verify here verifies that userRepository save method is only called once
        verify(userRepository,times(1)).save(user);

    }

    @Test(expected = UserAlreadyExistsException.class)
    public void saveUserTestFailure() throws UserAlreadyExistsException {
        when(userRepository.save(any())).thenReturn(null);
        User savedUser = userService.saveUser(user);
        System.out.println("savedUser" + savedUser);
        //Assert.assertEquals(user,savedUser);

       /*doThrow(new UserAlreadyExistException()).when(userRepository).findById(eq(101));
       userService.saveUser(user);*/
    }

    @Test
    public void getAllUsers() {
        //stubbing the mock to return specific data
        when(userRepository.findAll()).thenReturn(list);
        List<User> userlist = userService.getAllUsers();
        Assert.assertEquals(list, userlist);
    }

    @Test
    public void updateUserTestSuccess() throws UserNotFoundException {
        when(userRepository.existsById(any())).thenReturn(true);
        when(userRepository.save(any())).thenReturn(user);
        User savedUser = userService.updateUser(user);
        Assert.assertEquals(user, savedUser);

        //verify here verifies that userRepository save method is only called once
        verify(userRepository,times(1)).save(user);

    }

    @Test(expected = UserNotFoundException.class)
    public void updateUserTestFailure() throws UserNotFoundException {
        when(userRepository.existsById(anyInt())).thenReturn(false);
        when(userRepository.save(any())).thenReturn(null);
        User savedUser = userService.updateUser(user);

        //Assert.assertEquals(user,savedUser);

       /*doThrow(new UserAlreadyExistException()).when(userRepository).findById(eq(101));
       userService.saveUser(user);*/
    }

    @Test
    public void deleteUserTestSuccess() throws UserNotFoundException {
        when(userRepository.existsById(anyInt())).thenReturn(true);

        userService.deleteUser(user.getUserId());

        //verify here verifies that userRepository save method is only called once
        verify(userRepository,times(1)).deleteById(user.getUserId());

    }

    @Test(expected = UserNotFoundException.class)
    public void deleteUserTestFailure() throws UserNotFoundException {
        when(userRepository.existsById(anyInt())).thenReturn(false);
        userService.deleteUser(user.getUserId());
    }

    @Test
    public void getUserByIdTestSuccess() throws UserNotFoundException {
        when(userRepository.existsById(anyInt())).thenReturn(true);
        when(userRepository.findById(anyInt())).thenReturn(java.util.Optional.ofNullable(user));

        User fetchedUser = userService.getUserById(user.getUserId());
        Assert.assertEquals(user, fetchedUser);
        //verify here verifies that userRepository save method is only called once
        verify(userRepository,times(1)).findById(user.getUserId());

    }

    @Test(expected = UserNotFoundException.class)
    public void getUserByIdTestFailure() throws UserNotFoundException {
        when(userRepository.existsById((anyInt()))).thenReturn(false);
        userService.getUserById(user.getUserId());
    }

    @Test
    public void searchUserTest() {
        when(userRepository.findByUserCommentsContainingIgnoreCaseOrUserNameContainingIgnoreCase(anyString(),anyString())).thenReturn(list);
        List<User> searchResultsUser = userService.getUserByNameOrComments("user");
        Assert.assertEquals(searchResultsUser,list);

        //verify here verifies that userRepository save method is only called once
        verify(userRepository,times(1)).findByUserCommentsContainingIgnoreCaseOrUserNameContainingIgnoreCase("user","user");

    }

}