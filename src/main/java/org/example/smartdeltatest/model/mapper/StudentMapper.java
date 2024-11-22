package org.example.smartdeltatest.model.mapper;

import org.example.smartdeltatest.model.Student;
import org.example.smartdeltatest.model.dto.StudentDTO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface StudentMapper {

  StudentMapper INSTANCE = Mappers.getMapper(StudentMapper.class);

  // Маппинг сущности в DTO
  StudentDTO studentToStudentDTO(Student student);

  // Маппинг DTO в сущность
  Student studentDTOToStudent(StudentDTO studentDTO);
}