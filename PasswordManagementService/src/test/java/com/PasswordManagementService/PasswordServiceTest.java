package com.PasswordManagementService;

import com.PasswordManagementService.service.PasswordService;
import org.junit.jupiter.api.Test;
import static org.mockito.Mockito.*;

public class PasswordServiceTest {

    @Test
    public void testRequestPasswordReset() {
        PasswordService passwordService = mock(PasswordService.class);
        String email = "user@example.com";
        passwordService.requestPasswordReset(email);
        verify(passwordService, times(1)).requestPasswordReset(email);
    }
}
