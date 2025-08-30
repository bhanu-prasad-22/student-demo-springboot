package com.example.student_demo.controller;

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

@RestController
@RequestMapping("/students")
public class StudentController {

    private final StudentService studentService;

    // Constructor injection (recommended over @Autowired)
    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    // CREATE
    @PostMapping
    public ResponseEntity<Student> addStudent(@Valid @RequestBody Student student) {
        Student saved = studentService.create(student);
        return new ResponseEntity<>(saved, HttpStatus.CREATED);
    }

    // READ all
    @GetMapping
    public List<Student> getAllStudents() {
        return studentService.getAll();
    }


    // READ by ID
    @GetMapping("/{id}")
    public Student getStudentById(@PathVariable Long id) {
        return studentService.getById(id);
    }


    // GET /students/paginated?page=0&size=5&sort=name
    @GetMapping("/paginated")
    public Page<Student> getStudents(
            @RequestParam(defaultValue = "0") int page,   // page number (0-indexed)
            @RequestParam(defaultValue = "5") int size,  // size per page
            @RequestParam(defaultValue = "id") String sortBy,
            @RequestParam(defaultValue = "asc") String sortDir
    ) {
        Sort sort = sortDir.equalsIgnoreCase("asc") ? Sort.by(sortBy).ascending()
                : Sort.by(sortBy).descending();
        Pageable pageable = PageRequest.of(page, size, sort);
        return studentService.getAllPaginated(pageable);
    }
    // UPDATE
    @PutMapping("/{id}")
    public Student updateStudent(@PathVariable Long id, @Valid @RequestBody Student studentDetails) {
        return studentService.update(id, studentDetails);
    }

    // DELETE
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteStudent(@PathVariable Long id) {
        studentService.delete(id);
    }
}
