package com.example.finalproject_clinic.controller.security;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    @GetMapping
    public String home() {
        return "<h1>User </h1>";
    }


    @GetMapping("/admin")
    public ResponseEntity<String> admin() {
        return ResponseEntity.ok("<h1>Admin</h1>");
    }
}
