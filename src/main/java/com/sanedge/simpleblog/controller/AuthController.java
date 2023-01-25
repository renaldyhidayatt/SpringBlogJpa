package com.sanedge.simpleblog.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sanedge.simpleblog.dto.request.LoginRequest;
import com.sanedge.simpleblog.dto.request.RegisterRequest;
import com.sanedge.simpleblog.dto.response.MessageResponse;
import com.sanedge.simpleblog.service.impl.AuthServiceImpl;

import jakarta.validation.Valid;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    AuthServiceImpl authServiceImpl;

    @PostMapping("/login")
    public ResponseEntity<MessageResponse> loginUser(@Valid @RequestBody LoginRequest loginRequest) {
        MessageResponse messageResponse = this.authServiceImpl.loginUser(loginRequest);

        return ResponseEntity.ok(messageResponse);
    }

    @PostMapping("/register")
    public ResponseEntity<MessageResponse> registerUser(@Valid @RequestBody RegisterRequest registerRequest) {
        MessageResponse messageResponse = this.authServiceImpl.registerUser(registerRequest);

        return ResponseEntity.ok(messageResponse);
    }
}
