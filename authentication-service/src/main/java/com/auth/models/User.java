package com.auth.models;

import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "users")
public class User {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int user_id;
  @NotBlank
  private String password;

  @NotBlank
  @Size(max = 20)
  private String username;

  @ManyToMany(fetch = FetchType.LAZY)

  private Set<Role> roles = new HashSet<>();

  public User() {
  }

  public User(String username, String password) {
    this.username = username;
    this.password = password;
  }

  public int getId() {
    return user_id;
  }

  public void setId(int user_id) {
    this.user_id = user_id;
  }

  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public Set<Role> getRoles() {
    return roles;
  }

  public void setRoles(Set<Role> roles) {
    this.roles = roles;
  }
}
