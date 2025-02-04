package com.example.role_based_auth.controller;

import com.example.role_based_auth.entity.UserEntity;
import com.example.role_based_auth.service.DefaultUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/users")
@PreAuthorize("hasAuthority('USER')")
public class UserController {
    @Autowired
    private DefaultUserService userService;

    @GetMapping("/test")
    public String getTestMessage() {
        return "It is message from UserController!";
    }

    @GetMapping("/my")
    public ResponseEntity<?> getUserProfile(@RequestParam Long id) {
        try {
            return new ResponseEntity<>(userService.getUserById(id), HttpStatus.OK);
        } catch (UsernameNotFoundException e) {
            // logger
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }
}
