package com.aagam.user.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class LoginDto {

    @NotBlank(message = "Phone number is mandatory")
  @JsonProperty("phone")   private  String phoneNumber;

    @NotBlank(message = "Password is mandatory")
    private String password;
}