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

	public static void main(String[] args) {
		SpringApplication.run(RelationalDbApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		System.out.println("Application started");
	}
}
