package com.app.controller;


import com.app.entitie.User;
import com.app.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;


    // http://localhost:8080/user/save
    @PostMapping("/save")
    public ResponseEntity<User> createUser(@RequestBody User user){
        User user1 = userService.createUser(user);
        return new ResponseEntity<>(user1, HttpStatus.CREATED);
    }

    // http://localhost:8080/user
    @GetMapping
    public ResponseEntity<List<User>> getUsers(){
        List<User> users = userService.getUsers();
        return new ResponseEntity<>(users,HttpStatus.OK);
    }

    // http://localhost:8080/user/singleUser/{id}
    @GetMapping("/singleUser/{id}")
    public ResponseEntity<User> getSingleUser(@PathVariable int id){
        User singleUser = userService.getSingleUser(id);
        return new ResponseEntity<>(singleUser,HttpStatus.OK);
    }

    // http://localhost:8080/user/update/{id}
    @PutMapping("/update/{id}")
    public ResponseEntity<User> updateUser(@RequestBody User user,@PathVariable
    int id){
        User user1 = userService.updateUser(user, id);
        return new ResponseEntity<>(user1,HttpStatus.OK);
    }

    // http://localhost:8080/user/partial/{id}
    @PatchMapping("/partial/{id}")
    public ResponseEntity<User> partialUpdate(@RequestBody Map<String,
            Optional> map,@PathVariable int id){
        User user = userService.updatePartialUserData(map, id);
        return new ResponseEntity<>(user,HttpStatus.OK);
    }

    // http://localhost:8080/user/{id}
    @DeleteMapping("/{id}")
    public String deleteUser(@PathVariable int id){
        userService.deleteSingleUser(id);
        return "user deleted !";
    }
}
