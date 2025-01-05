package com.test.utils;

import com.auth.security.jwt.JwtUtils;
import com.auth.security.services.UserDetailsImpl;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.core.Authentication;

import java.util.Collections;

@ExtendWith(MockitoExtension.class)
public class JwtUtilsTest {
    @InjectMocks
    private JwtUtils jwtUtils;

    @Mock
    private Authentication authentication;

    @Test
    void testGenerateJwtToken() {

            UserDetailsImpl userDetails = new UserDetailsImpl(1, "testUser", "password", Collections.emptyList());
            Mockito.when(authentication.getPrincipal()).thenReturn(userDetails);

            String token = jwtUtils.generateJwtToken(authentication);

            // Check that the token is not null or empty
            Assertions.assertNotNull(token);
            Assertions.assertFalse(token.isEmpty(), "JWT token should not be empty");

            // Verify that the getPrincipal method was called once
            Mockito.verify(authentication, Mockito.times(1)).getPrincipal();


    }

    @Test
    void testValidateJwtToken() {
        String mockToken = "mockJwtToken";

        boolean isValid = jwtUtils.validateJwtToken(mockToken);

        Assertions.assertFalse(isValid); // If token is invalid.
    }
}
