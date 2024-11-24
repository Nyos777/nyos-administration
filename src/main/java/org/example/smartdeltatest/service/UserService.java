package org.example.smartdeltatest.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.smartdeltatest.model.dto.UserCreateDTO;
import org.example.smartdeltatest.model.dto.UserDTO;
import org.example.smartdeltatest.model.dto.UserLoginDTO;
import org.example.smartdeltatest.model.dto.UserTokenDTO;
import org.example.smartdeltatest.service.client.KeyCloakService;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class UserService {

    private final KeyCloakService keyCloakService;

    public UserDTO createUser(UserCreateDTO userCreateDTO){

        var userRepresentation = keyCloakService.createUser(userCreateDTO);

        return UserDTO.builder()
                .username(userRepresentation.getUsername())
                .email(userRepresentation.getEmail())
                .firstName(userRepresentation.getFirstName())
                .lastName(userCreateDTO.getLastName())
                .build();
    }

    public UserTokenDTO authenticate(UserLoginDTO userLoginDTO){
        return keyCloakService.signIn(userLoginDTO);
    }

}
