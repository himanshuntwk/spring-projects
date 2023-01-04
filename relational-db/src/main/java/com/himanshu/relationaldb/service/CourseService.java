package com.himanshu.relationaldb.service;

import com.himanshu.relationaldb.entity.Course;
import com.himanshu.relationaldb.repository.CourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CourseService {

  @Autowired
  CourseRepository courseRepository;

  public Course getCourse(Long id) {
      return courseRepository.findById(id).orElse(new Course());
  }
  public void saveCourse(Course course)
  {
    courseRepository.save(course);
  }
}
