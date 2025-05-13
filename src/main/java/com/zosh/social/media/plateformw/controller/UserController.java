package com.zosh.social.media.plateformw.controller;

import com.zosh.social.media.plateformw.models.User;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class UserController {

    @GetMapping("/users")

    // retrun type list of users
    public List<User> getUsers(){

        // liat of array
        List<User> users = new ArrayList<>();

        // class users data with the help of allargsconstructure
        User user1 = new User(1 ,"code","zosh","codewith@gamil.com","12345");
        User user2 = new User(2,"sunny","sharma","sunnysharma.org1@gmail.com","12345678");
        User user3 = new User(3,"sunder","yadav","sunnysharma.org1@gmail.com","12345678");

        // add usrs
        users.add(user1);
        users.add(user2);
        users.add(user3);

        // return users
        return users;

    }
    @GetMapping("/users/{userId}")

    // retrun type list of users
    public User getUserbyId(@PathVariable("userId") Integer id){


        // class users data with the help of allargsconstructure
        User user1 = new User(1 ,"code","zosh","codewith@gamil.com","12345");
        // add usrs

        user1.setId(id);
        // return users
        return user1;
    }
    @PostMapping("/users")
    public  User createUser(@RequestBody User user){

        User newUser = new User();
        newUser.setEmail(user.getEmail());
        newUser.setName(user.getName());
        newUser.setLastName(user.getLastName());
        newUser.setPassword(user.getPassword());
        newUser.setId(user.getId());

        return newUser;
    }

    @PutMapping("/users")
    public User updateUser(@RequestBody User user){

        User user1 = new User(1 ,"code","zosh","codewith@gamil.com","12345");

        if(user.getName()!=null){
            user1.setName(user.getName());
        }
        if(user.getLastName()!=null){
            user1.setLastName(user.getLastName());
        }
        if(user.getEmail()!=null){
            user1.setEmail(user.getEmail());
        }

        return user1;
    }

    @DeleteMapping("users/{userId}")
    public String deleteUser(@PathVariable("userId") Integer userId){


        return "user deleted successfully" + userId;

    }
}
