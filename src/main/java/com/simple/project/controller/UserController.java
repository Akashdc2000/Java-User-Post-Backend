package com.simple.project.controller;

import com.simple.project.entity.User;
import com.simple.project.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @Operation(summary = "This api is used to fetch data of all Users")
    @GetMapping("/user")
    public ResponseEntity<List<User>> getAllUsers() {
        return userService.getAllUsers();
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<User> getUserByUserId(@PathVariable(name = "userId") String userId) {
        return userService.getUserByUserId(userId);
    }

    @PostMapping("/user")
    public ResponseEntity<User> addNewUser(@RequestBody User user) {
        return userService.addUser(user);
    }

    @PutMapping("/user")
    public ResponseEntity<User> updateUser(@RequestBody User user) {
        return userService.updateUser(user);
    }

    @DeleteMapping("/user/{userId}")
    public ResponseEntity<Void> deleteUser(@PathVariable(name = "userId") String userId) {
        return userService.deleteUserByUserId(userId);
    }
}
