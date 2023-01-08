package com.himanshu.nativejdbc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class StudentController {

  @Autowired
  StudentRepository studentRepository;

  @GetMapping("/get-students")
  public List<Student> getAllStudents() {
    return studentRepository.findAllStudents();
  }
}
