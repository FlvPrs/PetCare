package com.atv.PetCare.config.security.dto;

public record LoginRequestDTO(
        String username,
        String password) {
}