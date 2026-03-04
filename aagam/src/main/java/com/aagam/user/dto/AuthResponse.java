package com.aagam.user.dto;

import com.aagam.user.entity.Role;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AuthResponse {
    private String token;
    private Long userId;
    private String name;
    private String email;
    @JsonProperty("phone") String phoneNumber;
    private Role role;
}