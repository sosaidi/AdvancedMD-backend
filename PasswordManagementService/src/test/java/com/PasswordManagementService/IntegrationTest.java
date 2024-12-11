package com.PasswordManagementService;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.beans.factory.annotation.Autowired;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
public class IntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void testRequestPasswordReset() throws Exception {
        mockMvc.perform(post("/api/auth/request-password-reset")
                        .param("email", "user@example.com"))
                .andExpect(status().isOk());
    }
}
