package com.himanshu.relationaldb.service;

import com.himanshu.relationaldb.entity.School;
import com.himanshu.relationaldb.entity.Student;
import com.himanshu.relationaldb.repository.SchoolRepository;
import com.himanshu.relationaldb.repository.StudentRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SchoolService {

  @Autowired
  SchoolRepository schoolRepository;

  public void saveSchool(School school) {
    schoolRepository.save(school);
  }

  public School getSchool(Long id) {
    return schoolRepository.findById(id).orElse(new School());
  }
}
