package com.himanshu.relationaldb.repository;

import com.himanshu.relationaldb.entity.Course;
import com.himanshu.relationaldb.entity.School;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SchoolRepository extends JpaRepository<School, Long> {
}
