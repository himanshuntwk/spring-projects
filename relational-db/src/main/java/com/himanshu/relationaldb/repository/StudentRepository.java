package com.himanshu.relationaldb.repository;

import com.himanshu.relationaldb.entity.Course;
import com.himanshu.relationaldb.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepository extends JpaRepository<Student, Long> {
}
