package com.springjwt.controllers;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/test")
public class TestController {
  @GetMapping("/all")
  @PreAuthorize("hasRole('PATIENT') or hasRole('DOCOTR') or hasRole('ADMIN')")
  public String allAccess() {
    return "Public Content.";
  }

  @GetMapping("/patients")
  @PreAuthorize("hasRole('PATIENT')")
  public String userAccess() {
    return "User Content.";
  }

  @GetMapping("/staff")
  @PreAuthorize("hasRole('STAFF')")
  public String moderatorAccess() {
    return "Staff.";
  }

  @GetMapping("/admin")
  @PreAuthorize("hasRole('ADMIN')")
  public String adminAccess() {
    return "Admin Board.";
  }
}
