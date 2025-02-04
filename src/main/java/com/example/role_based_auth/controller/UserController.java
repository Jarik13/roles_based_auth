package com.example.role_based_auth.controller;

import com.example.role_based_auth.service.DefaultUserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    @GetMapping("/test")
    public String getTestMessage() {
        logger.info("Request received from /api/users/test");
        return "It is message from UserController!";
    }

    @GetMapping("/my")
    public ResponseEntity<?> getUserProfile(@RequestParam Long id) {
        logger.info("Request received to /api/users/my with id: {}", id);
        try {
            logger.debug("Fetching user profile for id: {}", id);
            return new ResponseEntity<>(userService.getUserById(id), HttpStatus.OK);
        } catch (UsernameNotFoundException e) {
            logger.error("User not found with id: {}", id);
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            logger.error("Unexpected error while fetching user profile with id: {}", id);
            return new ResponseEntity<>("Unexpected error occurred!", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
