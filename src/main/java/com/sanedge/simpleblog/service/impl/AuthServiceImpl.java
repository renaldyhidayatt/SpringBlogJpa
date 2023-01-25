package com.sanedge.simpleblog.service.impl;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.sanedge.simpleblog.dto.request.LoginRequest;
import com.sanedge.simpleblog.models.User;
import com.sanedge.simpleblog.dto.request.RegisterRequest;
import com.sanedge.simpleblog.dto.response.AuthResponse;
import com.sanedge.simpleblog.dto.response.MessageResponse;
import com.sanedge.simpleblog.dto.response.RegisterResponse;
import com.sanedge.simpleblog.repository.UserRepository;
import com.sanedge.simpleblog.security.JwtProvider;
import com.sanedge.simpleblog.security.UserDetail;
import com.sanedge.simpleblog.service.AuthService;

@Service
public class AuthServiceImpl implements AuthService {
    private final AuthenticationManager authenticationManager;
    private final UserRepository userRepository;
    private final PasswordEncoder encoder;
    private final JwtProvider jwtProvider;

    @Autowired
    public AuthServiceImpl(AuthenticationManager authenticationManager, UserRepository userRepository,
            PasswordEncoder encoder, JwtProvider jwtProvider) {
        this.authenticationManager = authenticationManager;
        this.userRepository = userRepository;
        this.encoder = encoder;
        this.jwtProvider = jwtProvider;
    }

    @Override
    public MessageResponse loginUser(LoginRequest loginRequest) {
        Authentication authentication = this.authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);

        String jwt = jwtProvider.generateAccessToken(authentication);

        long expiresAt = jwtProvider.getjwtExpirationMs();
        Date date = new Date();
        date.setTime(expiresAt);

        UserDetail userDetails = (UserDetail) authentication.getPrincipal();

        AuthResponse authResponse = AuthResponse.builder().authenticationToken(jwt).expiresAt(date.toString())
                .username(userDetails.getUsername()).build();

        return MessageResponse.builder().message("Berhasil login").data(authResponse).statusCode(200).build();

    }

    @Override
    public MessageResponse registerUser(RegisterRequest registerRequest) {
        User user = new User();
        user.setUsername(registerRequest.getUsername());
        user.setEmail(registerRequest.getEmail());
        user.setPassword(encoder.encode(registerRequest.getPassword()));

        this.userRepository.save(user);

        RegisterResponse registerResponse = new RegisterResponse(user.getUsername(), user.getEmail());

        return MessageResponse.builder().message("Berhasil register").data(registerResponse).statusCode(200).build();
    }

}
