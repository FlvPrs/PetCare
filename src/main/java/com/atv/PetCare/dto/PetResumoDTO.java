package com.atv.PetCare.dto;

import java.util.UUID;

public record PetResumoDTO(
        UUID id,
        String nome,
        String especie,
        Integer idade
) {
}