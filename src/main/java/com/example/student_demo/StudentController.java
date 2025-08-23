package com.example.student_demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.config.ConfigDataResourceNotFoundException;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/students")
public class StudentController {

    @Autowired
    private StudentRepositary studentRepositary;

    //post
    @PostMapping
    public Student addStudent(@Valid @RequestBody  Student student)
    {
        return studentRepositary.save(student);

    }
    @GetMapping
    public List<Student> getAllStudents()
    {
        return studentRepositary.findAll();
    }
    @GetMapping("/{id}")
    public Student getStudentById(@PathVariable Long id)
    {
        return studentRepositary.findById(id)
                .orElseThrow(() -> new
            ResourceNotFoundException("Student not found with id: "+id));
    }


    @PutMapping("/{id}")
    public Student updateStudent(@PathVariable Long id, @Valid @RequestBody Student studentDetails) {
        return studentRepositary.findById(id)
                .map(student -> {
                    student.setName(studentDetails.getName());
                    student.setEmail(studentDetails.getEmail());
                    return studentRepositary.save(student);
                })
                .orElseThrow(() -> new RuntimeException("Student not found with id " + id));
    }


    @DeleteMapping("/{id}")
    public void deleteStudent(@PathVariable Long id)
    {
        Student student=studentRepositary.findById(id)
                .orElseThrow(() -> new RuntimeException("Student not found with id " + id));
        studentRepositary.delete(student);
    }

}
