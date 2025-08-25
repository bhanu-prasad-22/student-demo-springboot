package com.example.student_demo.service;

import com.example.student_demo.entity.Student;

import java.util.List;

public interface StudentService {
    Student create(Student s);
        Student getById(Long id);
        List<Student> getAll();
        Student update(Long id,Student s);
        void delete(Long id);
}
