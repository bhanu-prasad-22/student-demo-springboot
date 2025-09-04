// src/main/java/com/example/student_demo/service/StudentServiceImpl.java
package com.example.student_demo.service;

import com.example.student_demo.entity.Student;
import com.example.student_demo.repository.StudentRepositary;
import com.example.student_demo.exception.ResourceNotFoundException;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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
    @CachePut(value="students",key="#result.id")//cache new student
    public Student create(Student s) {
        Student student=new Student();
        student.setName(s.getName());
        student.setEmail(s.getEmail());
        student.setAge(s.getAge());
        student.setCourse(s.getCourse());
        return repo.save(student);
    }

    @Override
    @Transactional(readOnly = true)
    @Cacheable(value = "students",key="#id")//cache lookups by id
    public Student getById(Long id) {
        return repo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Student not found with id: " + id));
    }

    @Override
    @Transactional(readOnly = true)
    @Cacheable(value ="studentsAll" )//cache the entire list
    public List<Student> getAll() {
        return repo.findAll();
    }


    @Override
    @Transactional
    @CachePut(value = "students",key="#id")//update cache when student updated
    public Student update(Long id, Student s) {
        Student existing = getById(id);
        existing.setCourse(s.getCourse());
        existing.setName(s.getName());
        existing.setEmail(s.getEmail());
        existing.setAge(s.getAge());
        return repo.save(existing);
    }

    @Override
    @Transactional
    @CacheEvict(value = "students",key="#id")//remove cache entry when deleted
    public void delete(Long id) {
        Student existing = getById(id);
        repo.delete(existing);
    }

    @Override
    public Page<Student> getAllPaginated(Pageable pageable) {
              return repo.findAll(pageable);
    }
}