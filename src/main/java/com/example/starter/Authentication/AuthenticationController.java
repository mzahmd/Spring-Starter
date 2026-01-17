package com.example.starter.Authentication;

import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("task/login")
public class AuthenticationController {

    public ResponseEntity<String> login(@Valid @RequestBody AuthenticationRequestDTO authenticationRequestDTO) {
        return ResponseEntity.ok("User is valid");
    }
}
