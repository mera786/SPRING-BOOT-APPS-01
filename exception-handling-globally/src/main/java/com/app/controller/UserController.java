package com.app.controller;


import com.app.entity.User;
import com.app.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;


    // http://localhost:8081/users
    @PostMapping
    public User createUser(@RequestBody User user){
        User savedUser = userService.createUser(user);
        return savedUser;
    }


    // http://localhost:8081/users/{userId}
    @GetMapping("/{userId}")
    public User getSingleUser(@PathVariable(value = "userId") long userId){
        User singleUser = userService.getSingleUser(userId);
        return singleUser;
    }


    // http://localhost:8081/users/{userId}
    @PutMapping("/{userId}")
    public User updateSingleUser(@RequestBody User user,@PathVariable(value = "userId") long userId){
        User updateSingleUser = userService.updateSingleUser(user, userId);
        return updateSingleUser;
    }


    // http://localhost:8081/users/{userId}
    @DeleteMapping("/{userId}")
    public String deleteSingleUser(@PathVariable(value = "userId") long userId){
        userService.deleteSingleUser(userId);
        return userId+ ":user deleted successfully";
    }
}