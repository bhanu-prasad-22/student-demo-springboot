package com.example.student_demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class StudentDemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(StudentDemoApplication.class, args);
	}

}
