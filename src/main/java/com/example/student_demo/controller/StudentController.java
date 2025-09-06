package com.example.student_demo.controller;

import com.example.student_demo.dto.StudentRequest;
import com.example.student_demo.dto.StudentResponse;
import com.example.student_demo.entity.Student;
import com.example.student_demo.service.StudentService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/students")
public class StudentController {

    private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    // CREATE
    @PostMapping
    public ResponseEntity<StudentResponse> addStudent(@Valid @RequestBody StudentRequest request) {
        Student saved = studentService.create(toEntity(request));
        return new ResponseEntity<>(toResponse(saved), HttpStatus.CREATED);
    }

    // READ all
    @GetMapping
    public List<StudentResponse> getAllStudents() {
        return studentService.getAll()
                .stream()
                .map(this::toResponse)
                .collect(Collectors.toList());
    }

    // READ by ID
    @GetMapping("/{id}")
    public StudentResponse getStudentById(@PathVariable Long id) {
        return toResponse(studentService.getById(id));
    }

    // PAGINATED
    @GetMapping("/paginated")
    public Page<StudentResponse> getStudents(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5") int size,
            @RequestParam(defaultValue = "id") String sortBy,
            @RequestParam(defaultValue = "asc") String sortDir
    ) {
        Sort sort = sortDir.equalsIgnoreCase("asc") ? Sort.by(sortBy).ascending()
                : Sort.by(sortBy).descending();
        Pageable pageable = PageRequest.of(page, size, sort);
        return studentService.getAllPaginated(pageable).map(this::toResponse);
    }

    // UPDATE
    @PutMapping("/{id}")
    public StudentResponse updateStudent(@PathVariable Long id, @Valid @RequestBody StudentRequest request) {
        Student updated = studentService.update(id, toEntity(request));
        return toResponse(updated);
    }

    // DELETE
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteStudent(@PathVariable Long id) {
        studentService.delete(id);
    }

    // ===================== MAPPERS =====================
    private Student toEntity(StudentRequest request) {
        Student student = new Student();
        student.setName(request.getName());
        student.setEmail(request.getEmail());
        student.setAge(request.getAge());
        student.setCourse(request.getCourse());
        return student;
    }

    private StudentResponse toResponse(Student student) {
        StudentResponse response = new StudentResponse();
        response.setId(student.getId());
        response.setName(student.getName());
        response.setEmail(student.getEmail());
        response.setAge(student.getAge());
        response.setCourse(student.getCourse());
        return response;
    }
}
