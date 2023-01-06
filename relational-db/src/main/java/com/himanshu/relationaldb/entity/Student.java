package com.himanshu.relationaldb.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Data
@Entity
public class Student {

  @Id
  @GeneratedValue
  private Long id;
  private String studentName;

  @JsonBackReference
  @ManyToOne
  private School school;

  @JsonManagedReference
  @ManyToMany(fetch = FetchType.EAGER)
  @JoinTable(name = "student_course",
      joinColumns = @JoinColumn(name = "student_id"),
      inverseJoinColumns = @JoinColumn(name = "course_id")
  )
  private List<Course> courses;

  @JsonManagedReference
  @OneToOne
  private StudentAddress address;
}
