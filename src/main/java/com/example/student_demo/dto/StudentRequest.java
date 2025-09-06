package com.example.student_demo.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;

public class StudentRequest {

    @NotBlank(message = "Name cannot be blank")
    private String name;

    @Email(message = "Email must be valid")
    private String email;

    @Min(value = 16, message = "Age must be at least 16")
    private int age;

    @NotBlank(message = "Course cannot be blank")
    private String course;

    public StudentRequest() {}

    public StudentRequest(String name, String email, int age, String course) {
        this.name = name;
        this.email = email;
        this.age = age;
        this.course = course;
    }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public int getAge() { return age; }
    public void setAge(int age) { this.age = age; }

    public String getCourse() { return course; }
    public void setCourse(String course) { this.course = course; }
}
