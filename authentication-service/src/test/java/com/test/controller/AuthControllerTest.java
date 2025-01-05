package com.test.controller;



import com.auth.controllers.AuthController;
import com.auth.models.ERole;
import com.auth.models.Role;
import com.auth.models.User;
import com.auth.payload.request.LoginRequest;
import com.auth.payload.request.SignupRequest;
import com.auth.payload.response.JwtResponse;
import com.auth.payload.response.MessageResponse;
import com.auth.repository.RoleRepository;
import com.auth.repository.UserRepository;
import com.auth.security.jwt.JwtUtils;
import com.auth.security.services.UserDetailsImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Collections;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class AuthControllerTest {

    @Mock
    private AuthenticationManager authenticationManager;

    @Mock
    private UserRepository userRepository;

    @Mock
    private RoleRepository roleRepository;

    @Mock
    private PasswordEncoder passwordEncoder;
    @Mock
    private JwtUtils jwtUtils;



    @InjectMocks
    private AuthController authController;


    @Test
    void authenticateUser_ReturnsJwtResponse() {
        // Mock data
        LoginRequest loginRequest = new LoginRequest();
        loginRequest.setUsername("testUser");
        loginRequest.setPassword("password");

        UserDetailsImpl userDetails = new UserDetailsImpl(1, "testUser", "password", Collections.emptyList());

        // Mock Authentication object
        Authentication authentication = mock(Authentication.class);
        when(authenticationManager.authenticate(any(UsernamePasswordAuthenticationToken.class)))
                .thenReturn(authentication);
        when(authentication.getPrincipal()).thenReturn(userDetails);
        when(jwtUtils.generateJwtToken(authentication)).thenReturn("mockJwt");

        // Call the method
        ResponseEntity<?> response = authController.authenticateUser(loginRequest);

        // Validate
        JwtResponse jwtResponse = (JwtResponse) response.getBody();
        assertEquals("mockJwt", jwtResponse.getAccessToken());
        assertEquals("testUser", jwtResponse.getUsername());
        verify(authenticationManager, times(1)).authenticate(any(UsernamePasswordAuthenticationToken.class));
    }


    @Test
    void registerUser_UsernameAlreadyExists_ReturnsBadRequest() {
        // Mock data
        SignupRequest signupRequest = new SignupRequest();
        signupRequest.setUsername("existingUser");

        when(userRepository.existsByUsername("existingUser")).thenReturn(true);

        // Call the method
        ResponseEntity<?> response = authController.registerUser(signupRequest);

        // Validate
        MessageResponse messageResponse = (MessageResponse) response.getBody();
        assertEquals("Error: Username is already taken!", messageResponse.getMessage());
        assertEquals(400, response.getStatusCodeValue());
    }

    @Test
    void registerUser_ValidUser_ReturnsSuccessMessage() {
        // Mock data
        SignupRequest signupRequest = new SignupRequest();
        signupRequest.setUsername("newUser");
        signupRequest.setPassword("password");
        signupRequest.setRole(new HashSet<>(Set.of("ROLE_ADMIN")));

        User user = new User("newUser", "encodedPassword");
        Role adminRole = new Role(ERole.ROLE_ADMIN);

        when(userRepository.existsByUsername("newUser")).thenReturn(false);
        when(passwordEncoder.encode("password")).thenReturn("encodedPassword");
        when(roleRepository.findByName(ERole.ROLE_ADMIN)).thenReturn(Optional.of(adminRole));
        when(userRepository.save(any(User.class))).thenReturn(user);

        // Call the method
        ResponseEntity<?> response = authController.registerUser(signupRequest);

        // Validate
        MessageResponse messageResponse = (MessageResponse) response.getBody();
        assertEquals("User registered successfully!", messageResponse.getMessage());
        assertEquals(200, response.getStatusCodeValue());
        verify(userRepository, times(1)).save(any(User.class));
    }

    @Test
    void logoutUser_ReturnsLogoutMessage() {
        // Call the method
        ResponseEntity<?> response = authController.logoutUser();

        // Validate
        MessageResponse messageResponse = (MessageResponse) response.getBody();
        assertEquals("User logged out successfully", messageResponse.getMessage());
        assertEquals(200, response.getStatusCodeValue());
    }
}
