package com.PasswordManagementService.controller;

import com.PasswordManagementService.dto.PasswordResetRequest;
import com.PasswordManagementService.dto.PasswordUpdateRequest;
import com.PasswordManagementService.service.PasswordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/password")
public class PasswordController {

    @Autowired
    private PasswordService passwordService;

    @PostMapping("/reset-request")
    public ResponseEntity<String> requestResetLink(@RequestBody PasswordResetRequest request) {
        passwordService.sendResetLink(request.getEmail());
        return ResponseEntity.ok("Password reset link sent to your email!");
    }

    @PostMapping("/update")
    public ResponseEntity<String> updatePassword(@RequestBody PasswordUpdateRequest request) {
        passwordService.updatePassword(request.getToken(), request.getNewPassword());
        return ResponseEntity.ok("Password updated successfully!");
    }
}
