package com.binted.Binted.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity(name = "app_user")
public class UserEntity {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  private String username;
  private String password;

  public Long getId() {
    return id;
  }

  public String getUsername() {
    return username;
  }

  public UserEntity setUsername(String username) {
    this.username = username;
    return this;
  }

  public String getPassword() {
    return password;
  }

  public UserEntity setPassword(String password) {
    this.password = password;
    return this;
  }
}
