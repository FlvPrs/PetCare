package com.atv.PetCare.dto;

import java.util.UUID;

public record OwnerResumoDTO(
        UUID id,
        String nome
) {
}