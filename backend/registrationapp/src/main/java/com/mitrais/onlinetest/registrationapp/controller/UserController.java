package com.mitrais.onlinetest.registrationapp.controller;
/*
 * Dear Maintainer,
 *
 * When I wrote this code, only I and God knew what it was. Now, only God knows!
 *
 * So, If you're done, trying to 'optimize' this routine (and failed).
 * Please, increment the following counter as a warning to the next guy:
 * total_hours_wasted_here: 999;
 *
 * Sincerely Yours, Hooman
 */

import com.mitrais.onlinetest.registrationapp.exception.ResourceNotFoundException;
import com.mitrais.onlinetest.registrationapp.persistence.entity.User;
import com.mitrais.onlinetest.registrationapp.persistence.repository.UserRepository;
import com.mitrais.onlinetest.registrationapp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v0/api")
public class UserController {
    @Autowired
    UserService userService;

    @GetMapping("/users")
    public List<User> getAllUsers() {
        return userService.findAll();
    }

    @PostMapping("/users")
    public User createUser(@RequestBody User user) {
        return userService.createUser(user);
    }

    @GetMapping("/users/{id}")
    public User getUserById(@PathVariable(value = "id") Long userId) {
        return userService.getUserById(userId);
    }

    @PutMapping("/users/{id}")
    public User updateUser(@PathVariable(value = "id") Long userId,
                           @RequestBody User userDetails) {
        return userService.updateUser(userId, userDetails);
    }

    @DeleteMapping("/users/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable(value = "id") Long userId) {
        userService.deleteUser(userId);
        return ResponseEntity.ok().build();
    }
}
