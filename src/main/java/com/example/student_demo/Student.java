package com.example.student_demo;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import io.swagger.v3.oas.annotations.media.Schema;

@Entity
@Table(
        name = "students",
        uniqueConstraints = @UniqueConstraint(name = "uk_student_email", columnNames = "email")
)
@Schema(description = "Student entity representing a student record")
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(description = "Unique ID of the student", example = "1")
    private Long id;

    @NotBlank(message = "Name is required")
    @Size(max = 50, message = "Name must be <= 50 characters")
    @Schema(description = "Full name of the student", example = "Bhanu Prasad")
    private String name;

    @Email(message = "Invalid email")
    @Size(max = 100, message = "Email must be <= 100 characters")
    @Schema(description = "Email of the student", example = "bhanu@example.com")
    private String email;

    @Min(value = 1, message = "Age must be greater than 0")
    @Max(value = 120, message = "Age must be <= 120")
    @Schema(description = "Age of the student", example = "20")
    private Integer age;

    @NotBlank(message = "Course is required")
    @Size(max = 50, message = "Course name must be <= 50 characters")
    @Schema(description = "Course of the student", example = "Computer Science")
    private String course;

    // Default constructor (required for JPA & Springdoc)
    public Student() {}

    // Parameterized constructor (optional, for convenience)
    public Student(String name, String email, Integer age, String course) {
        this.name = name;
        this.email = email;
        this.age = age;
        this.course = course;
    }

    // Getters and setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public Integer getAge() { return age; }
    public void setAge(Integer age) { this.age = age; }

    public String getCourse() { return course; }
    public void setCourse(String course) { this.course = course; }
}