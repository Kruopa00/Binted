package com.binted.Binted.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Entity(name = "record")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RecordEntity {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  private String date;
  private Double weight;
  private Integer reps;
  private Integer sets;
  private String comment;
  @ManyToOne
  @OnDelete(action = OnDeleteAction.CASCADE)
  @JoinColumn(name = "exercise_id")
  private ExerciseEntity exercise;

  public Long getId() {
    return id;
  }

  public String getDate() {
    return date;
  }

  public RecordEntity setDate(String date) {
    this.date = date;
    return this;
  }

  public Double getWeight() {
    return weight;
  }

  public RecordEntity setWeight(Double weight) {
    this.weight = weight;
    return this;
  }

  public Integer getReps() {
    return reps;
  }

  public RecordEntity setReps(Integer reps) {
    this.reps = reps;
    return this;
  }

  public Integer getSets() {
    return sets;
  }

  public RecordEntity setSets(Integer sets) {
    this.sets = sets;
    return this;
  }

  public String getComment() {
    return comment;
  }

  public RecordEntity setComment(String comment) {
    this.comment = comment;
    return this;
  }

  public ExerciseEntity getExercise() {
    return exercise;
  }

  public RecordEntity setExercise(ExerciseEntity exercise) {
    this.exercise = exercise;
    return this;
  }
}
