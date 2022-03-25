package com.example.javaspringcourseproject;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.boot.autoconfigure.web.servlet.error.ErrorMvcAutoConfiguration;

//@EnableAutoConfiguration(exclude = {ErrorMvcAutoConfiguration.class})

@SpringBootApplication
public class JavaSpringCourseProjectApplication {

	public static void main(String[] args) {
		SpringApplication.run(JavaSpringCourseProjectApplication.class, args);
	}

}
