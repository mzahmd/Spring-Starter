package com.example.starter.Authentication;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record AuthenticationRequestDTO(

        @NotBlank(message = "Email field is empty")
        @Email(message = "Email field is not correct")
        String email,

        @NotBlank
        String password) {
}
