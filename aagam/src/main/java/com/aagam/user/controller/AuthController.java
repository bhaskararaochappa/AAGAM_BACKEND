package com.aagam.user.controller;

import com.aagam.user.dto.UserRegisterDto;
import com.aagam.user.dto.LoginDto;
import com.aagam.user.dto.AuthResponse;
import com.aagam.user.service.UserService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final UserService userService;
    public AuthController(UserService userService){ this.userService = userService; }

    @PostMapping("/register")
    public AuthResponse register(@Valid @RequestBody UserRegisterDto dto){
        System.out.println(dto.getPhoneNumber());
        System.out.println(dto.getPassword());
        System.out.println(dto.getName());

        AuthResponse auth=userService.register(dto);
        System.out.println(auth);

        return  auth;

    }

    @PostMapping("/login")
    public AuthResponse login(@Valid @RequestBody LoginDto dto){
        System.out.println(dto.getPhoneNumber());
        return userService.login(dto);
    }
}