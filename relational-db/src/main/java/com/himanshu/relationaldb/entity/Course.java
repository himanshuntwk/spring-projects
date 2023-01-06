package com.himanshu.relationaldb.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Data
@Entity
public class Course {
  @Id
  @GeneratedValue
  private Long id;
  private String courseName;
  @ManyToMany(mappedBy = "courses")
  @JsonBackReference
  List<Student> students;
}
