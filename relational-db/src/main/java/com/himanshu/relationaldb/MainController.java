package com.himanshu.relationaldb;

import com.himanshu.relationaldb.entity.Course;
import com.himanshu.relationaldb.entity.School;
import com.himanshu.relationaldb.entity.Student;
import com.himanshu.relationaldb.service.CourseService;
import com.himanshu.relationaldb.service.SchoolService;
import com.himanshu.relationaldb.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class MainController {

  private CourseService courseService;
  private SchoolService schoolService;
  private StudentService studentService;

  @Autowired
  public MainController(CourseService courseService, SchoolService schoolService, StudentService studentService) {
    this.courseService = courseService;
    this.studentService = studentService;
    this.schoolService = schoolService;
  }

  @PostMapping("/save-course")
  public ResponseEntity<String> saveCourse(@RequestBody Course course) {
    courseService.saveCourse(course);
    return new ResponseEntity<>("Course saved", HttpStatus.OK);
  }

  @PostMapping("/save-school")
  public ResponseEntity<String> saveSchool(@RequestBody School school) {
    schoolService.saveSchool(school);
    return new ResponseEntity<>("School saved", HttpStatus.OK);
  }

  @PostMapping("/save-student")
  public ResponseEntity<String> saveStudent(@RequestBody Student student) {
    studentService.saveStudent(student);
    return new ResponseEntity<>("Student saved", HttpStatus.OK);
  }

  @GetMapping("/get-student")
  public Student getStudent(@RequestParam String id) {
    Student student = studentService.getStudent(Long.valueOf(id));
    return student;
  }

  @GetMapping("/get-school")
  public School getSchool(@RequestParam String id) {
    School school = schoolService.getSchool(Long.valueOf(id));
    return school;
  }
}
