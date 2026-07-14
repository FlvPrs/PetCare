package com.atv.PetCare.dto;

import java.util.UUID;

public record PetResponseDTO(UUID id,
	    String nome,
	    String especie,
	    Integer idade) {

}
