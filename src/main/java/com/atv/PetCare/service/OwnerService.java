package com.atv.PetCare.service;

import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.atv.PetCare.dto.OwnerCreateDTO;
import com.atv.PetCare.dto.OwnerDetalheDTO;
import com.atv.PetCare.dto.OwnerResponseDTO;
import com.atv.PetCare.entity.Owner;
import com.atv.PetCare.exception.OwnerNotFoundException;
import com.atv.PetCare.mapper.OwnerMapper;
import com.atv.PetCare.repository.OwnerRepository;

@Service
public class OwnerService {

    private static final Logger log =
            LoggerFactory.getLogger(OwnerService.class);

    private final OwnerRepository repository;
    private final OwnerMapper mapper;

    public OwnerService(
            OwnerRepository repository,
            OwnerMapper mapper) {

        this.repository = repository;
        this.mapper = mapper;
    }

    public OwnerDetalheDTO buscarPorId(UUID id) {

        log.info("Iniciando busca do tutor [{}]", id);

        Owner owner = repository.buscarComPets(id)
                .orElseThrow(() -> {

                    log.warn(
                        "Tutor [{}] não encontrado",
                        id);

                    return new OwnerNotFoundException(
                            "Tutor não encontrado");
                });

        log.info(
                "Tutor [{}] encontrado com {} pets",
                id,
                owner.getPets() != null
                        ? owner.getPets().size()
                        : 0);

        return mapper.entityToDetalheDto(owner);
    }

    public OwnerResponseDTO criar(
            OwnerCreateDTO dto) {

        log.info(
                "Iniciando criação do tutor [{}]",
                dto.nome());

        Owner owner =
                mapper.createDtoToEntity(dto);

        Owner salvo =
                repository.save(owner);

        log.info(
                "Tutor criado com sucesso. Id [{}], Nome [{}]",
                salvo.getId(),
                salvo.getNome());

        return mapper.entityToResponseDto(salvo);
    }
}