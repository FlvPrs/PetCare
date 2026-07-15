package com.atv.PetCare.dto;

import java.util.UUID;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;

public record PetCreateDTO(
		 @NotBlank
		String nome,
		 @NotBlank
	    String especie,
	    @Min(0)
	    Integer idade,
        UUID ownerId){

}
