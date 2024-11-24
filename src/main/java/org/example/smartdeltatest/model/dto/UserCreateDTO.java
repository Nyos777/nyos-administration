package org.example.smartdeltatest.model.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class UserCreateDTO {

    private String email;

    private String firstName;

    private String lastName;

    private String username;

    private String password;

}
