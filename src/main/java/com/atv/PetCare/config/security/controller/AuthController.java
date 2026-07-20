package com.atv.PetCare.config.security.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.atv.PetCare.config.security.dto.LoginRequestDTO;
import com.atv.PetCare.config.security.dto.LoginResponseDTO;
import com.atv.PetCare.config.security.service.AuthService;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final AuthService service;

    public AuthController(
            AuthService service) {

        this.service = service;
    }

    @PostMapping("/login")
    public LoginResponseDTO login(
            @RequestBody LoginRequestDTO dto) {

        return service.login(dto);
    }
}