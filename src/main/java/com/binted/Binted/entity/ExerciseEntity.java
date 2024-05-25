package com.binted.Binted.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity(name = "exercise")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ExerciseEntity {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  private String name;
  private String goal;
  @ManyToOne
  @JoinColumn(name = "user_id")
  private UserEntity user;

  public Long getId() {
    return id;
  }

  public String getName() {
    return name;
  }

  public ExerciseEntity setName(String name) {
    this.name = name;
    return this;
  }

  public String getGoal() {
    return goal;
  }

  public ExerciseEntity setGoal(String goal) {
    this.goal = goal;
    return this;
  }

  public UserEntity getUser() {
    return user;
  }

  public ExerciseEntity setUser(UserEntity user) {
    this.user = user;
    return this;
  }
}
