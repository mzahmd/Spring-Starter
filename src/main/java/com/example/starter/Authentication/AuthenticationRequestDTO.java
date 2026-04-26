package com.example.starter.Authentication;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

public record AuthenticationRequestDTO(

        @NotBlank(message = "Email field is empty")
//        @Email(message = "Email field is not correct")
        @Pattern(
                regexp = "^[A-Za-z0-9._%+-]+@gmail\\.com$",
                message = "Email field is not correct"
        )
        String email,

        @NotBlank
        String password) {
}
