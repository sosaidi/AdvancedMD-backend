package com.PasswordManagementService;

import com.PasswordManagementService.controller.PasswordController;
import com.PasswordManagementService.service.PasswordService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
public class PasswordControllerTest {

    @Mock
    private PasswordService passwordService;

    @InjectMocks
    private PasswordController passwordController;

    @Test
    public void testRequestPasswordReset() {
        String email = "user@example.com";
        doNothing().when(passwordService).requestPasswordReset(email);

        ResponseEntity<String> response = passwordController.requestPasswordReset(email);

        assertEquals("Password reset link sent to your email.", response.getBody());
    }
}
