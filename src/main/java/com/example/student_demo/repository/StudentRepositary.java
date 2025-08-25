package com.example.student_demo.repository;

import com.example.student_demo.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepositary extends JpaRepository<Student,Long>{}