package com.atv.PetCare.dto;

import java.util.List;
import java.util.UUID;

public record OwnerDetalheDTO(
        UUID id,
        String nome,
        String telefone,
        List<PetResumoDTO> pets
) {
}