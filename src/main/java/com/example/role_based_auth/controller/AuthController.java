package com.example.role_based_auth.controller;

import com.example.role_based_auth.dto.UserLoginDto;
import com.example.role_based_auth.dto.UserRegisterDto;
import com.example.role_based_auth.repository.UserRepository;
import com.example.role_based_auth.service.DefaultUserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {
    @Autowired
    private DefaultUserService userService;

    private static final Logger logger = LoggerFactory.getLogger(AuthController.class);

    @GetMapping("/test")
    public String getTestMessage() {
        logger.info("Request received from /api/auth/test");
        return "It is message from AuthController!";
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody UserRegisterDto userDto) {
        logger.info("Request received to /api/auth/register");
        try {
            logger.debug("Starting register user!");
            return new ResponseEntity<>(userService.save(userDto), HttpStatus.OK);
        } catch (Exception e) {
            logger.error("Unexpected error while saving user!");
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody UserLoginDto userDto) {
        logger.info("Request received to /api/auth/login");
        try {
            logger.debug("Starting login user!");
            return new ResponseEntity<>(userService.loadUserByUsername(userDto.getUsername()), HttpStatus.OK);
        } catch (Exception e) {
            logger.error("Unexpected error while getting user!");
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }
}
