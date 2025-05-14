package com.project.security;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;

// REST controller for login and registration
@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final UserService userService;

    // Register a new user
    @PostMapping("/register")
    public ResponseEntity<String> register(@Valid @RequestBody UserDTO userDTO) {
        userService.register(userDTO);
        return ResponseEntity.status(201).body("User registered successfully");
    }

    // Login and return JWT
    @PostMapping("/login")
    public ResponseEntity<String> login(@Valid @RequestBody UserDTO userDTO) {
        String message = userService.login(userDTO);
        return ResponseEntity.ok(message);
    }

    @PostMapping("/verify")
    public ResponseEntity<String> verify(@RequestParam String username, @RequestParam String otp) {
        String token = userService.verify(username, otp);
        return ResponseEntity.ok(token);
    }

    @PostMapping("/forgot-password")
    public ResponseEntity<String> forgotPassword(@RequestParam String username) {
        String message = userService.forgotPassword(username);
        return ResponseEntity.ok(message);
    }

    @PostMapping("/reset-password")
    public ResponseEntity<String> resetPassword(@RequestParam String username,
                                                @RequestParam String password,
                                                @RequestParam String otp) {
        String message = userService.resetPassword(username, otp, password);
        return ResponseEntity.ok(message);
    }
}