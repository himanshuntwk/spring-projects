package com.himanshu.relationaldb.service;

import com.himanshu.relationaldb.entity.Course;
import com.himanshu.relationaldb.entity.Student;
import com.himanshu.relationaldb.repository.CourseRepository;
import com.himanshu.relationaldb.repository.StudentRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StudentService {

  @Autowired
  StudentRepository studentRepository;

  public void saveStudent(Student student) {
    studentRepository.save(student);
  }

  @Transactional
  public Student getStudent(Long id) {
    Student student = studentRepository.findById(id).orElse(new Student());
    return student;
  }
}
