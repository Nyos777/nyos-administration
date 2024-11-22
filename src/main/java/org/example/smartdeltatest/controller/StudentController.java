package org.example.smartdeltatest.controller;

import org.example.smartdeltatest.model.Student;
import org.example.smartdeltatest.model.dto.StudentDTO;
import org.example.smartdeltatest.service.StudentService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/students")
public class StudentController {

  private final StudentService service;

  public StudentController(StudentService service) {
    this.service = service;
  }

  @GetMapping
  public ResponseEntity<List<StudentDTO>> getAllStudents() {
    return ResponseEntity.ok(service.getAllStudents());
  }

  @PostMapping
  public ResponseEntity<StudentDTO> saveStudent(@RequestBody StudentDTO student) {
    return ResponseEntity.ok(service.saveStudent(student));
  }

  @PutMapping("/{id}")
  public ResponseEntity<StudentDTO> updateStudent(@PathVariable String id, @RequestBody StudentDTO student) {
    return ResponseEntity.ok(service.updateStudent(id, student));
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Void> deleteStudent(@PathVariable String id) {
    service.deleteStudent(id);
    return ResponseEntity.noContent().build();
  }
}
