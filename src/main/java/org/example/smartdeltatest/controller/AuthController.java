package org.example.smartdeltatest.controller;

import lombok.RequiredArgsConstructor;
import org.example.smartdeltatest.model.dto.UserLoginDTO;
import org.example.smartdeltatest.model.dto.UserTokenDTO;
import org.example.smartdeltatest.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/login")
@RequiredArgsConstructor
public class AuthController {

    private final UserService userService;

    @PostMapping(value = "/token")
    public ResponseEntity<UserTokenDTO> login(@RequestBody UserLoginDTO userLoginDTO){

        return new ResponseEntity<>(userService.authenticate(userLoginDTO), HttpStatus.OK);
    }
}
