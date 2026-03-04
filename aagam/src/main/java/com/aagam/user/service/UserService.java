package com.aagam.user.service;

import com.aagam.user.dto.UserRegisterDto;
import com.aagam.user.dto.LoginDto;
import com.aagam.user.dto.AuthResponse;

public interface UserService {
    AuthResponse register(UserRegisterDto dto);
    AuthResponse login(LoginDto dto);
}