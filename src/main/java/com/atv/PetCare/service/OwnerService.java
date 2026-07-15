package com.atv.PetCare.service;

import java.util.UUID;

import org.springframework.stereotype.Service;

import com.atv.PetCare.dto.OwnerCreateDTO;
import com.atv.PetCare.dto.OwnerDetalheDTO;
import com.atv.PetCare.dto.OwnerResponseDTO;
import com.atv.PetCare.entity.Owner;
import com.atv.PetCare.mapper.OwnerMapper;
import com.atv.PetCare.repository.OwnerRepository;

@Service
public class OwnerService {

    private final OwnerRepository repository;
    private final OwnerMapper mapper;

    public OwnerService(
            OwnerRepository repository,
            OwnerMapper mapper) {

        this.repository = repository;
        this.mapper = mapper;
    }
    
    public OwnerDetalheDTO buscarPorId(UUID id) {

    	Owner owner = repository.buscarComPets(id)
                .orElseThrow(() ->
                        new RuntimeException(
                                "Tutor não encontrado"));

        return mapper.entityToDetalheDto(owner);
    }

    public OwnerResponseDTO criar(
            OwnerCreateDTO dto) {

        Owner owner =
                mapper.createDtoToEntity(dto);

        Owner salvo =
                repository.save(owner);

        return mapper.entityToResponseDto(salvo);
    }
}