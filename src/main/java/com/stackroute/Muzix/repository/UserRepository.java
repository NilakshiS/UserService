package com.stackroute.Muzix.repository;

import com.stackroute.Muzix.domain.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

//Repository class used to perform database operations, extends JpaRepository
//MongoRepository<EntityClassName,WrapperTypeOfIdPropertyInEntityClass>
@Repository
public interface UserRepository extends MongoRepository<User, Integer> {

    //Query to search a user by its name or comments in the database
    //used lower() to make searching case insensitive
    List<User> findByUserCommentsContainingIgnoreCaseOrUserNameContainingIgnoreCase(String comments, String name);
//    @Query("SELECT t FROM User t WHERE lower(t.userName) LIKE lower(CONCAT('%',:string,'%')) OR lower(t.userComments) LIKE lower(CONCAT('%',:string,'%'))")
//    List<User> findUserByNameOrComments(String string);
}
