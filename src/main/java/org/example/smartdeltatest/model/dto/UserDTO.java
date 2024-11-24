package org.example.smartdeltatest.model.dto;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
public class UserDTO {

    private String username;

    private String email;

    private String firstName;

    private String lastName;
}
