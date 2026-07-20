package com.atv.PetCare.config.security.service;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import com.atv.PetCare.config.security.dto.LoginRequestDTO;
import com.atv.PetCare.config.security.dto.LoginResponseDTO;

@Service
public class AuthService {

    private final AuthenticationManager authenticationManager;

    private final JwtService jwtService;

    public AuthService(
            AuthenticationManager authenticationManager,
            JwtService jwtService) {

        this.authenticationManager =
                authenticationManager;

        this.jwtService = jwtService;
    }

    public LoginResponseDTO login(
            LoginRequestDTO dto) {

        Authentication authentication =
                authenticationManager.authenticate(

                        new UsernamePasswordAuthenticationToken(
                                dto.username(),
                                dto.password()));

        UserDetails user =
                (UserDetails)
                        authentication.getPrincipal();

        String token =
                jwtService.gerarToken(user);

        return new LoginResponseDTO(token);
    }
}