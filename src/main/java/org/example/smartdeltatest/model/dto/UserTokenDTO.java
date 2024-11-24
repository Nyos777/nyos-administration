package org.example.smartdeltatest.model.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class UserTokenDTO {

    private String token;

    private String refreshToken;
}
