package com.atv.PetCare.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import com.atv.PetCare.dto.PetCreateDTO;
import com.atv.PetCare.dto.PetUpdateDTO;
import com.atv.PetCare.dto.PetResponseDTO;
import com.atv.PetCare.entity.Pet;

@Mapper(componentModel = "spring")
public interface PetMapper {

    // Create DTO -> Entity
    @Mapping(target = "id", ignore = true)
    Pet createDtoToEntity(PetCreateDTO dto);

    // Entity -> Create DTO
    PetCreateDTO entityToCreateDto(Pet pet);

    // Update DTO -> Entity
    @Mapping(target = "id", ignore = true)
    Pet updateDtoToEntity(PetUpdateDTO dto);

    // Entity -> Update DTO
    PetUpdateDTO entityToUpdateDto(Pet pet);

    // Entity -> Response DTO
    PetResponseDTO entityToResponseDto(Pet pet);

    // Response DTO -> Entity
    Pet responseDtoToEntity(PetResponseDTO dto);

    // Atualiza entidade existente com os dados do DTO
    @Mapping(target = "id", ignore = true)
    void updateEntityFromDto(
            PetUpdateDTO dto,
            @MappingTarget Pet pet);
}