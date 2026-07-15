package com.atv.PetCare.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.atv.PetCare.dto.AppointmentCreateDTO;
import com.atv.PetCare.dto.AppointmentResponseDTO;
import com.atv.PetCare.dto.OwnerResumoDTO;
import com.atv.PetCare.dto.PetResumoDTO;
import com.atv.PetCare.entity.Appointment;
import com.atv.PetCare.entity.Owner;
import com.atv.PetCare.entity.Pet;

@Mapper(componentModel = "spring")
public interface AppointmentMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "pet", ignore = true)
    Appointment createDtoToEntity(
            AppointmentCreateDTO dto);

    @Mapping(source = "pet", target = "pet")
    @Mapping(source = "pet.owner", target = "owner")
    AppointmentResponseDTO entityToResponseDto(
            Appointment appointment);

    PetResumoDTO petToResumoDto(Pet pet);

    OwnerResumoDTO ownerToResumoDto(Owner owner);
}