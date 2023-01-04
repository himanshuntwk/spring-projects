package com.himanshu.relationaldb;

import com.himanshu.relationaldb.entity.Course;
import com.himanshu.relationaldb.entity.School;
import com.himanshu.relationaldb.entity.Student;
import com.himanshu.relationaldb.service.CourseService;
import com.himanshu.relationaldb.service.SchoolService;
import com.himanshu.relationaldb.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.List;

@SpringBootApplication
public class RelationalDbApplication implements CommandLineRunner {

	@Autowired
	CourseService courseService;
	@Autowired
	StudentService studentService;
	@Autowired
	SchoolService schoolService;
	public static void main(String[] args) {
		SpringApplication.run(RelationalDbApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		System.out.println("Start>>");
//		Course course = new Course();
//		course.setCourseName("computer");
//		courseService.saveCourse(course);
//		Course course2 = new Course();
//		course2.setCourseName("mechanical");
//		courseService.saveCourse(course2);
//
//		Student himanshu = new Student();
//		School school = new School();
//		school.setSchoolName("KPS");
//		himanshu.setStudentName("Himanshu");
//		himanshu.setSchool(school);
//		schoolService.saveSchool(school);
//		Student rahul = new Student();
//		rahul.setStudentName("Rahul");
//		rahul.setSchool(school);
//		himanshu.setCourses(List.of(course,course2));
//		rahul.setCourses(List.of(course,course2));
//		course.setStudents(List.of(himanshu, rahul));
//		course2.setStudents(List.of(himanshu, rahul));
//
//		studentService.saveStudent(himanshu);
//		studentService.saveStudent(rahul);
//		System.out.println(studentService.getStudent(1l).getSchool().getSchoolName());
//		System.out.println("Course size :: " + studentService.getStudent(1l).getCourses().size());
//		School schoolDb = schoolService.getSchool(1l);
//		int size = schoolDb.getStudents().size();
//		System.out.println("Students >> " + size);
	}
}
