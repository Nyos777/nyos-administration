package org.example.smartdeltatest.controller;

import lombok.RequiredArgsConstructor;
import org.example.smartdeltatest.model.dto.UserCreateDTO;
import org.example.smartdeltatest.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping("/create")
    public ResponseEntity<?> createUser(@RequestBody UserCreateDTO userCreateDTO){
        return new ResponseEntity<>(userService.createUser(userCreateDTO), HttpStatus.CREATED);
    }


}
