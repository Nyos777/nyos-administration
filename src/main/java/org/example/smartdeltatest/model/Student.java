package org.example.smartdeltatest.model;

import lombok.Data;
import lombok.Getter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "student")
@Data
public class Student {
  @Id
  private String id;
  private String lastName;
  private String firstName;
  private String middleName;
  private String group;
  private Double averageGrade;
}
