package com.sanedge.simpleblog.service;

import com.sanedge.simpleblog.dto.request.LoginRequest;
import com.sanedge.simpleblog.dto.request.RegisterRequest;
import com.sanedge.simpleblog.dto.response.MessageResponse;

public interface AuthService {
    MessageResponse loginUser(LoginRequest loginRequest);

    MessageResponse registerUser(RegisterRequest registerRequest);
}
