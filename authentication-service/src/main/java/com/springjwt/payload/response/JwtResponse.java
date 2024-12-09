package com.springjwt.payload.response;

import java.util.List;

public class JwtResponse {
  private String token;
  private String type = "Bearer";
  private int user_id;
  private String username;
  private List<String> roles;

  public JwtResponse(String accessToken, int user_id, String username, List<String> roles) {
    this.token = accessToken;
    this.user_id = user_id;
    this.username = username;
    this.roles = roles;
  }

  public String getAccessToken() {
    return token;
  }

  public void setAccessToken(String accessToken) {
    this.token = accessToken;
  }

  public String getTokenType() {
    return type;
  }

  public void setTokenType(String tokenType) {
    this.type = tokenType;
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

  public List<String> getRoles() {
    return roles;
  }
}
