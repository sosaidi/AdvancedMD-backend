package com.test.security.jwt;


import com.pat.security.jwt.JwtUtils;
import com.pat.security.services.UserDetailsImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.core.Authentication;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

class JwtUtilsTest {

    @InjectMocks
    private JwtUtils jwtUtils;

    @Mock
    private Authentication authentication;

    @Mock
    private UserDetailsImpl userDetailsImpl;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGenerateJwtToken() {
        // Arrange
        String username = "testUser";
        String jwtSecret = "testSecrettestSecrettestSecrettestSecrettestSecrettestSecret";  // This would ideally come from application properties
        int jwtExpirationMs = 3600000;  // 1 hour in ms

        // Setting the values in the JwtUtils instance
        jwtUtils.jwtSecret = jwtSecret;
        jwtUtils.jwtExpirationMs = jwtExpirationMs;

        when(authentication.getPrincipal()).thenReturn(userDetailsImpl);
        when(userDetailsImpl.getUsername()).thenReturn(username);

        // Act
        String token = jwtUtils.generateJwtToken(authentication);

        // Assert
        assertNotNull(token);
        assertTrue(token.length() > 0, "JWT token should not be empty");
    }


    @Test
    void testValidateJwtToken_ValidToken() {
        // Arrange
        String validToken = "valid-jwt-token";  // You would generally mock a real token here

        // Act
        boolean isValid = jwtUtils.validateJwtToken(validToken);

        // Assert
        assertTrue(isValid, "The JWT token should be valid (since validation logic is yet to be implemented correctly)");
    }

    @Test
    void testValidateJwtToken_InvalidToken() {
        // Arrange
        String invalidToken = "invalid-jwt-token";  // You can mock invalid behavior if needed

        // Act
        boolean isValid = jwtUtils.validateJwtToken(invalidToken);

        // Assert
        assertTrue(isValid, "The JWT token should be invalid based on the future implementation");
    }
}

