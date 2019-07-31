package com.stackroute.Muzix.domain;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;


//@Entity used to mark it as a database entity
@Document
//Lombok annotations
//Lombok plugin automatically generates getters, setters and constructors for any class marked as @Data
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User {

    //properties
    @Id     //annotated with @Id to make it primary key in database
    //@GeneratedValue(strategy = GenerationType.AUTO)     //To make it an auto-incremented id
    @ApiModelProperty(notes = "The User id")           //Swagger2's annotation to describe properties
                                                        // in the documentation
    private int userId;                                //User's id

    @ApiModelProperty(notes = "The name of the User")
    private String userName;                           //Name of the user

    @ApiModelProperty(notes = "The comments about the User")
    private String userComments;                       //comments about the user, used to store artist name
}
