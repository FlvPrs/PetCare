package com.atv.PetCare.dto;

import java.util.UUID;

public record OwnerResponseDTO(
        UUID id,
        String nome,
        String telefone
) {
}