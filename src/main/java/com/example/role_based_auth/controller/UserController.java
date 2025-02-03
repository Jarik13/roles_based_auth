package com.example.role_based_auth.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/users")
@PreAuthorize("hasAuthority('USER')")
public class UserController {
    @GetMapping("/test")
    public String getTestMessage() {
        return "It is message from UserController!";
    }
}
