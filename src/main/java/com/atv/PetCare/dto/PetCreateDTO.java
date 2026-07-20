package com.atv.PetCare.dto;

import java.util.UUID;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;

public record PetCreateDTO(
		@NotBlank 
		@Schema(description = "Nome do pet", example = "Rex") 
		String nome,
		@NotBlank 
		@Schema(description = "Espécie", example = "Cachorro") 
		String especie,
		@Min(0) 
		@Schema(description = "Idade", example = "5") 
		Integer idade,
		@Schema(description = "ID do tutor") 
		UUID ownerId) {

}
