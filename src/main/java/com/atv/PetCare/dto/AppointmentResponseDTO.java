package com.atv.PetCare.dto;

import java.time.LocalDateTime;
import java.util.UUID;

public record AppointmentResponseDTO(
        UUID id,
        PetResumoDTO pet,
        OwnerResumoDTO owner,
        LocalDateTime dataHora,
        String observacao
) {
}