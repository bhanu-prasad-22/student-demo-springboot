package com.example.student_demo;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;


@Entity
@Table(name ="students",
uniqueConstraints = @UniqueConstraint(name = "uk_student_email",columnNames = "email"))
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NotBlank(message ="Name is required" )
    @Size(max =50 ,message= "name must be <=50 chars")
    private String name;

    @Email(message = "Invalid email")
    @Size(max =100 ,message= "Email must be <=100 chars")
    private String email;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
