package org.example.smartdeltatest.service;

import org.example.smartdeltatest.model.Student;
import org.example.smartdeltatest.model.dto.StudentDTO;
import org.example.smartdeltatest.model.mapper.StudentMapper;
import org.example.smartdeltatest.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class StudentService {

  private final StudentRepository repository;
  private final StudentMapper studentMapper;

  @Autowired
  public StudentService(StudentRepository repository, StudentMapper studentMapper) {
    this.repository = repository;
    this.studentMapper = studentMapper;
  }

  public List<StudentDTO> getAllStudents() {
    try {
      List<Student> students = repository.findAll();
      if (students.isEmpty()) {
        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No students found");
      }
      return students.stream()
              .map(studentMapper::studentToStudentDTO) // Преобразуем сущности в DTO
              .collect(Collectors.toList());
    } catch (Exception e) {
      throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Error fetching students", e);
    }
  }

  public StudentDTO saveStudent(StudentDTO studentDTO) {
    try {
      Student student = studentMapper.studentDTOToStudent(studentDTO);  // Преобразуем DTO в сущность
      Student savedStudent = repository.save(student);
      return studentMapper.studentToStudentDTO(savedStudent);  // Возвращаем DTO
    } catch (Exception e) {
      throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Error saving student", e);
    }
  }

  public StudentDTO updateStudent(String id, StudentDTO studentDTO) {
    try {
      if (!repository.existsById(id)) {
        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Student not found");
      }
      Student student = studentMapper.studentDTOToStudent(studentDTO);  // Преобразуем DTO в сущность
      student.setId(id);
      Student updatedStudent = repository.save(student);
      return studentMapper.studentToStudentDTO(updatedStudent);  // Возвращаем DTO
    } catch (Exception e) {
      throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Error updating student", e);
    }
  }

  public void deleteStudent(String id) {
    try {
      if (!repository.existsById(id)) {
        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Student not found");
      }
      repository.deleteById(id);
    } catch (Exception e) {
      throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Error deleting student", e);
    }
  }
}
