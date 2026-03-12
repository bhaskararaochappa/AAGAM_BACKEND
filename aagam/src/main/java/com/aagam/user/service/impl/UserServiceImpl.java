package com.aagam.user.service.impl;



import com.aagam.security.JwtService;
import com.aagam.user.dto.UserRegisterDto;
import com.aagam.user.dto.LoginDto;
import com.aagam.user.dto.AuthResponse;
import com.aagam.user.entity.User;
import com.aagam.user.entity.Role;
import com.aagam.user.repository.UserRepository;
import com.aagam.user.service.UserService;
import org.springframework.stereotype.Service;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Set;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;

    public UserServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder, JwtService jwtService){
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtService = jwtService;
    }

    @Override
    public AuthResponse register(UserRegisterDto dto) {
        if(userRepository.existsByPhoneNumber(dto.getPhoneNumber())){
            System.out.println(dto.getPhoneNumber()+"AlReady Exists");
            throw new RuntimeException("Phone number already registered");

        }
        if(userRepository.existsByEmail(dto.getEmail())){
            System.out.println(dto.getEmail()+"AlReady Exists");
            throw new RuntimeException("Email already registered");
        }

        User user = User.builder()
                .name(dto.getName())
                .email(dto.getEmail())
                .phoneNumber(dto.getPhoneNumber())
                .password(passwordEncoder.encode(dto.getPassword()))
                .roles(Set.of(Role.ADMIN)) // default role
                .build();

        userRepository.save(user);

        String token = jwtService.generateToken(
                user.getPhoneNumber(),
                user.getRoles().iterator().next().name()
        );

        return new AuthResponse(token, user.getId(), user.getName(), user.getEmail(), user.getPhoneNumber(), user.getRoles().iterator().next());
    }

    @Override
    public AuthResponse login(LoginDto dto) {
        User user = userRepository.findByPhoneNumber(dto.getPhoneNumber())
                .orElseThrow(() -> new RuntimeException("User not found"));

        if(!passwordEncoder.matches(dto.getPassword(), user.getPassword())){
            throw new RuntimeException("Invalid password");
        }

        String token = jwtService.generateToken(
                user.getPhoneNumber(),
                user.getRoles().iterator().next().name()
        );


        return new AuthResponse(token, user.getId(), user.getName(), user.getEmail(), user.getPhoneNumber(),
                user.getRoles().iterator().next());
    }
}