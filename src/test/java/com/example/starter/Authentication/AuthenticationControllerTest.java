package com.example.starter.Authentication;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

//@ExtendWith(MockitoExtension.class)
class AuthenticationControllerTest {

    // @Mock => kein JUnit test durch Mockito
    // @Autowired => geht nur wenn der Spring Context gestartet wird
    AuthenticationController authenticationController = new AuthenticationController();


    @Test
    void login() throws Exception {
        // Given
        AuthenticationRequestDTO dto = new AuthenticationRequestDTO("test@test.com", "password");

        // When
        ResponseEntity<String> res = authenticationController.login(dto);

        // Then
        Assertions.assertEquals(HttpStatus.OK, res.getStatusCode());
        Assertions.assertEquals("User is valid", res.getBody());

    }
}