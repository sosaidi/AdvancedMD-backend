package com.PasswordManagementService.service;

import com.PasswordManagementService.entity.User;
import com.PasswordManagementService.repository.UserRepository;
import com.PasswordManagementService.util.TokenGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PasswordService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private EmailService emailService;

    public void sendResetLink(String email) {
        Optional<User> userOptional = userRepository.findByEmail(email);
        if (userOptional.isEmpty()) {
            throw new IllegalArgumentException("Email not found");
        }

        User user = userOptional.get();
        String token = TokenGenerator.generateToken();
        user.setResetToken(token);
        userRepository.save(user);

        String resetLink = "http://localhost:8080/api/password/update?token=" + token;
        emailService.sendEmail(user.getEmail(), "Password Reset Request", "Click here to reset your password: " + resetLink);
    }

    public void updatePassword(String token, String newPassword) {
        Optional<User> userOptional = userRepository.findByResetToken(token);
        if (userOptional.isEmpty()) {
            throw new IllegalArgumentException("Invalid token");
        }

        User user = userOptional.get();
        user.setPassword(newPassword); // Encrypt password in real scenarios
        user.setResetToken(null);
        userRepository.save(user);
    }
}
