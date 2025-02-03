package com.example.role_based_auth.controller;

import com.example.role_based_auth.dto.UserLoginDto;
import com.example.role_based_auth.dto.UserRegisterDto;
import com.example.role_based_auth.repository.UserRepository;
import com.example.role_based_auth.service.DefaultUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {
    @Autowired
    private DefaultUserService userService;

    @GetMapping("/test")
    public String getTestMessage() {
        return "It is message from AuthController!";
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody UserRegisterDto userDto) {
        try {
            return new ResponseEntity<>(userService.save(userDto), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody UserLoginDto userDto) {
        try {
            return new ResponseEntity<>(userService.loadUserByUsername(userDto.getUsername()), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }
}
