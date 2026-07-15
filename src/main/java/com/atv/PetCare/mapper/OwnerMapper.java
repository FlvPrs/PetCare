package com.atv.PetCare.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.atv.PetCare.dto.OwnerCreateDTO;
import com.atv.PetCare.dto.OwnerDetalheDTO;
import com.atv.PetCare.dto.OwnerResponseDTO;
import com.atv.PetCare.dto.PetResumoDTO;
import com.atv.PetCare.entity.Owner;
import com.atv.PetCare.entity.Pet;

@Mapper(componentModel = "spring")
public interface OwnerMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "pets", ignore = true)
    Owner createDtoToEntity(OwnerCreateDTO dto);

    OwnerResponseDTO entityToResponseDto(Owner owner);
    
    PetResumoDTO petToResumoDto(Pet pet);

    OwnerDetalheDTO entityToDetalheDto(Owner owner);
}