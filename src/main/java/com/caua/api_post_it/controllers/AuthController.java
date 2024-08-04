package com.caua.api_post_it.controllers;
import com.caua.api_post_it.dto.UserDTO;

import com.caua.api_post_it.services.AuthService;
import com.caua.api_post_it.services.TokenService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {
    @Autowired
    private AuthService authService;


    @PostMapping(value="/login",consumes = MediaType.APPLICATION_JSON_VALUE,produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity login(@RequestBody UserDTO userDTO) {
        return authService.login(userDTO);
    }
    @PostMapping(value = "/register",consumes = MediaType.APPLICATION_JSON_VALUE,produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity register(@RequestBody UserDTO userDTO){
        return authService.register(userDTO);
    }
}
