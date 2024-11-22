package org.example.smartdeltatest.model.dto;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class StudentDTO {
  private String firstName;
  private String lastName;
  private String middleName;
  private String group;
  private Double averageGrade;
}