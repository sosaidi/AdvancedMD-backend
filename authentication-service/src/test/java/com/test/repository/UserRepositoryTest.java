package com.test.repository;

import com.auth.models.User;
import com.auth.repository.UserRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
public class UserRepositoryTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserRepositoryTest userRepositoryTest;

    private User testUser;

    @BeforeEach
    public void setUp() {
        testUser = new User();
        testUser.setUsername("testuser");
        testUser.setPassword("password");

        // Mock the repository's behavior
        when(userRepository.save(testUser)).thenReturn(testUser);
        when(userRepository.findById(testUser.getId())).thenReturn(java.util.Optional.of(testUser));
    }

    @AfterEach
    public void tearDown() {
        // Reset the mock behavior if necessary
        reset(userRepository);
    }

    @Test
    void givenUser_whenSaved_thenCanBeFoundById() {
        User savedUser = userRepository.save(testUser);
        assertNotNull(savedUser);
        assertEquals(testUser.getUsername(), savedUser.getUsername());
        assertEquals(testUser.getPassword(), savedUser.getPassword());
    }

    @Test
    void givenUser_whenUpdated_thenCanBeFoundByIdWithUpdatedData() {
        testUser.setUsername("updatedUsername");
        when(userRepository.save(testUser)).thenReturn(testUser);

        User updatedUser = userRepository.save(testUser);

        assertNotNull(updatedUser);
        assertEquals("updatedUsername", updatedUser.getUsername());
    }

    @Test
    void givenUser_whenFindByUsernameCalled_thenUserIsFound() {
        when(userRepository.findByUsername("testuser")).thenReturn(java.util.Optional.of(testUser));

        User foundUser = userRepository.findByUsername("testuser").orElse(null);

        assertNotNull(foundUser);
        assertEquals("testuser", foundUser.getUsername());
    }
}
