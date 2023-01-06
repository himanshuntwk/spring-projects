package com.himanshu.relationaldb.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Data
@Entity
public class School {
  @Id
  @GeneratedValue
  private Long id;
  private String schoolName;

  @JsonManagedReference
  @OneToMany(mappedBy = "school", fetch = FetchType.EAGER)
  private List<Student> students;
}
