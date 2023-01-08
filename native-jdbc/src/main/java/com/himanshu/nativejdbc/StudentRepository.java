package com.himanshu.nativejdbc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class StudentRepository {

  @Autowired
  JdbcTemplate jdbcTemplate;

  public List<Student> findAllStudents() {
    return jdbcTemplate.query("select * from student", new BeanPropertyRowMapper<>(Student.class));
  }
}
