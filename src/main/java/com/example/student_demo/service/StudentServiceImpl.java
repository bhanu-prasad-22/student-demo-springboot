// src/main/java/com/example/student_demo/service/StudentServiceImpl.java
package com.example.student_demo.service;

import com.example.student_demo.Student;
import com.example.student_demo.StudentRepositary;
import com.example.student_demo.ResourceNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class StudentServiceImpl implements StudentService {

    private final StudentRepositary repo;

    public StudentServiceImpl(StudentRepositary repo) {
        this.repo = repo;
    }

    @Override
    @Transactional
    public Student create(Student s) {
        return repo.save(s);
    }

    @Override
    @Transactional(readOnly = true)
    public Student getById(Long id) {
        return repo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Student not found with id: " + id));
    }

    @Override
    @Transactional(readOnly = true)
    public List<Student> getAll() {
        return repo.findAll();
    }

    @Override
    @Transactional
    public Student update(Long id, Student s) {
        Student existing = getById(id);
        existing.setName(s.getName());
        existing.setEmail(s.getEmail());
        existing.setAge(s.getAge());
        existing.setCourse(s.getCourse());
        return repo.save(existing);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        Student existing = getById(id);
        repo.delete(existing);
    }
}