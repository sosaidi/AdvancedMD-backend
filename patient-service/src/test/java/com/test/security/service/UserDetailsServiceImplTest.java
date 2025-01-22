package com.test.security.service;


import com.pat.models.User;
import com.pat.repository.UserRepository;
import com.pat.security.services.UserDetailsServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

public class UserDetailsServiceImplTest {

    private UserDetailsServiceImpl userDetailsService;
    private UserRepository userRepository;

    @BeforeEach
    void setUp() {
        userRepository = Mockito.mock(UserRepository.class);
        userDetailsService = new UserDetailsServiceImpl();
        userDetailsService.userRepository = userRepository;
    }

    @Test
    void loadUserByUsername_UserExists_ReturnsUserDetails() {
        // Given
        String username = "testUser";
        User mockUser = new User(username, "testPassword"); // Replace null with roles if needed
        Mockito.when(userRepository.findByUsername(username)).thenReturn(Optional.of(mockUser));

        // When
        UserDetails userDetails = userDetailsService.loadUserByUsername(username);

        // Then
        assertNotNull(userDetails);
        assertEquals(username, userDetails.getUsername());
    }

    @Test
    void loadUserByUsername_UserDoesNotExist_ThrowsUsernameNotFoundException() {
        // Given
        String username = "nonExistingUser";
        Mockito.when(userRepository.findByUsername(username)).thenReturn(Optional.empty());

        // When & Then
        UsernameNotFoundException exception = assertThrows(UsernameNotFoundException.class,
                () -> userDetailsService.loadUserByUsername(username));
        assertEquals("User Not Found with username: " + username, exception.getMessage());
    }
}
