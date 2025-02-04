package com.example.role_based_auth.controller;

import com.example.role_based_auth.service.DefaultUserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/admins")
@PreAuthorize("hasAuthority('ADMIN')")
public class AdminController {
    @Autowired
    private DefaultUserService userService;

    private static final Logger logger = LoggerFactory.getLogger(AdminController.class);

    @GetMapping("/test")
    public String getTestMessage() {
        logger.info("Request received from /api/admins/test");
        return "It is message from AdminController!";
    }

    @GetMapping
    public ResponseEntity<?> getUserByEmail(@RequestParam String email) {
        logger.info("Request received from /api/admins?email={}", email);
        try {
            logger.debug("Fetching user details for email={}", email);
            return new ResponseEntity<>(userService.getUserByEmail(email), HttpStatus.OK);
        } catch (UsernameNotFoundException e) {
            logger.error("User not found with email={}", email);
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            logger.error("Unexpected error while fetching user details with email: {}", email);
            return new ResponseEntity<>("Unexpected error occurred!", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getUserById(@PathVariable Long id) {
        logger.info("Request received to /api/admins/{}", id);
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

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteUserById(@PathVariable Long id) {
        logger.info("Received user details from /api/admins/{}", id);
        try {
            logger.debug("Deleting user with id: {}", id);
            return new ResponseEntity<>(userService.deleteUser(id), HttpStatus.OK);
        } catch (UsernameNotFoundException e) {
            logger.error("User for deleting not found with id: {}", id);
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            logger.error("Unexpected error while deleting user with id: {}", id);
            return new ResponseEntity<>("Unexpected error occurred!", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
