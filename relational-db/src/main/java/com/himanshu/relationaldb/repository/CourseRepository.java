package com.himanshu.relationaldb.repository;

import com.himanshu.relationaldb.entity.Course;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CourseRepository extends JpaRepository<Course, Long> {
}
