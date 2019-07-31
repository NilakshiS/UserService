package com.stackroute.Muzix.repository;

import com.stackroute.Muzix.domain.User;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@DataMongoTest
public class UserRepositoryTest {

    @Autowired
    UserRepository userRepository;
    User user;

    @Before
    public void setUp()
    {
        user = new User(1,"Love Maze","BTS");
    }

    @After
    public void tearDown(){

        userRepository.deleteAll();
    }


    @Test
    public void testSaveUser(){
        userRepository.save(user);
        User fetchUser = userRepository.findById(user.getUserId()).get();
        Assert.assertEquals(user.getUserId(),fetchUser.getUserId());
        userRepository.delete(user);
    }

    @Test
    public void testSaveUserFailure(){
        User testUser = new User(2,"Fake Love","BTS");
        userRepository.save(testUser);
        User fetchUser = userRepository.findById(testUser.getUserId()).get();
        Assert.assertNotSame(testUser, user);
        userRepository.delete(testUser);
    }

    @Test
    public void testDeleteUser(){
        userRepository.save(user);
        Assert.assertNotNull(userRepository.findById(user.getUserId()).orElse(null));
        userRepository.delete(user);
        User fetchUser = userRepository.findById(user.getUserId()).orElse(null);
        Assert.assertNull(fetchUser);
    }

    @Test
    public void testFindUserById(){
        userRepository.save(user);
        User fetchUser = userRepository.findById(user.getUserId()).orElse(null);
        Assert.assertNotNull(fetchUser);
        userRepository.deleteAll();
    }

    @Test
    public void testFindUserByNameOrComments(){
        userRepository.save(user);
        List<User> fetchUser = userRepository.findByUserCommentsContainingIgnoreCaseOrUserNameContainingIgnoreCase("BTS","BTS");
        Assert.assertNotNull(fetchUser);
        userRepository.deleteAll();
    }

    @Test
    public void testGetAllUser(){
        User user = new User(2,"Fake Love","BTS");
        User user1 = new User(3,"DNA","BTS");
        userRepository.save(user);
        userRepository.save(user1);

        List<User> list = userRepository.findAll();
        Assert.assertEquals("Fake Love",list.get(0).getUserName());
        userRepository.deleteAll();
    }
}
