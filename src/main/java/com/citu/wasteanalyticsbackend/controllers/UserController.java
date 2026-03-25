package com.citu.wasteanalyticsbackend.controllers;

import com.citu.wasteanalyticsbackend.entities.User;
import com.citu.wasteanalyticsbackend.services.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v3")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }


    @PostMapping("users/add")
    public ResponseEntity<String> addUsers(@RequestBody User userData) throws Exception {
        String id = userService.addUser("users", userData);

        return ResponseEntity.status(HttpStatus.CREATED).body("User Created with ID: " + id);
    }

    @GetMapping("/users")
    public ResponseEntity<List<Map<String, Object>>> getUsers() throws Exception {
        List<Map<String, Object>> users = userService.getAllUsers("users");
        return ResponseEntity.status(HttpStatus.OK).body(users);
    }

    @GetMapping("/users/{username}")
    public ResponseEntity<List<Map<String, Object>>> getUserByUsername(@PathVariable String username) throws Exception {
        return ResponseEntity.status(HttpStatus.OK).body(userService.getByUsername("users", username));
    }

    @DeleteMapping("users/{username}")
    public ResponseEntity<String> deleteUserByUsername(@PathVariable String username) throws Exception {
        boolean result = userService.deleteUser("users", username);
        if (!result) {
            return ResponseEntity.status((HttpStatus.OK)).body("User with username: \"" + username + "\" does not exist.");
        }
        return ResponseEntity.status((HttpStatus.OK)).body("User with username: \"" + username + "\" successfuly deleted.");

    }

    @PatchMapping("users/{username}")
    public ResponseEntity<String> updateUser(@PathVariable String username,@RequestBody Map<String, Object> updates) throws Exception {
        boolean result = userService.updateUser("users", username, updates);
        if (!result)
            return ResponseEntity.status((HttpStatus.OK)).body("User with username: \"" + username + "\" does not exist.");
        return ResponseEntity.status((HttpStatus.OK)).body("User with username: \"" + username + "\" successfuly updated.");
    }
}
