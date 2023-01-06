package com.himanshu.relationaldb.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import lombok.Data;

@Data
@Entity
public class StudentAddress {
  @Id
  private Long id;
  @JsonBackReference
  @OneToOne(mappedBy = "address")
  private Student student;
  private String location;
}
