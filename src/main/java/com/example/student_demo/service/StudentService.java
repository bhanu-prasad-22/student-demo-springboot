package com.example.student_demo.service;

import com.example.student_demo.entity.Student;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface StudentService {
    Student create(Student s);
        Student getById(Long id);
        List<Student> getAll();
        Page<Student> getAll(Pageable pageable);
        Student update(Long id,Student s);
        void delete(Long id);
}
