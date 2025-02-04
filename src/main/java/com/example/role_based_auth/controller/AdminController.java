package com.example.role_based_auth.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/admins")
@PreAuthorize("hasAuthority('ADMIN')")
public class AdminController {
    @GetMapping("/test")
    public String getTestMessage() {
        return "It is message from AdminController!";
    }
}
