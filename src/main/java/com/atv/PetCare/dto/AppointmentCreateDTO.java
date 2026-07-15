package com.atv.PetCare.dto;

import java.util.UUID;

public record AppointmentCreateDTO(
        UUID petId,
        String observacao
) {
}