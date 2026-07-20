package com.atv.PetCare.service;

import java.util.List;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.atv.PetCare.dto.PetCreateDTO;
import com.atv.PetCare.dto.PetResponseDTO;
import com.atv.PetCare.dto.PetUpdateDTO;
import com.atv.PetCare.entity.Owner;
import com.atv.PetCare.entity.Pet;
import com.atv.PetCare.exception.PetNotFoundException;
import com.atv.PetCare.mapper.PetMapper;
import com.atv.PetCare.repository.OwnerRepository;
import com.atv.PetCare.repository.PetRepository;

@Service
public class PetService {

    private static final Logger log =
            LoggerFactory.getLogger(PetService.class);

    private final PetMapper mapper;
    private final PetRepository petRepository;
    private final OwnerRepository ownerRepository;

    public PetService(
            PetRepository petRepository,
            PetMapper mapper,
            OwnerRepository ownerRepository) {

        this.ownerRepository = ownerRepository;
        this.mapper = mapper;
        this.petRepository = petRepository;
    }

    public Page<PetResponseDTO> listar(Pageable pageable) {

    	log.info(
                "Buscando pets. Página [{}], Tamanho [{}]",
                pageable.getPageNumber(),
                pageable.getPageSize());

        Page<Pet> pets =
                petRepository.findAll(pageable);

        return pets.map(mapper::entityToResponseDto);
    }

    public List<PetResponseDTO> listarPorEspecie(
            String especie) {

        log.info(
                "Buscando pets da espécie [{}]",
                especie);

        List<PetResponseDTO> pets =
                petRepository.findByEspecie(especie)
                        .stream()
                        .map(mapper::entityToResponseDto)
                        .toList();

        log.info(
                "Encontrados {} pets da espécie [{}]",
                pets.size(),
                especie);

        return pets;
    }

    public List<PetResponseDTO> listarIdadeMaiorQue(
            Integer idade) {

        log.info(
                "Buscando pets com idade maior que [{}]",
                idade);

        List<PetResponseDTO> pets =
                petRepository.findByIdadeGreaterThan(idade)
                        .stream()
                        .map(mapper::entityToResponseDto)
                        .toList();

        log.info(
                "Encontrados {} pets com idade maior que [{}]",
                pets.size(),
                idade);

        return pets;
    }

    public PetResponseDTO criar(PetCreateDTO dto) {

        log.info(
                "Iniciando criação do pet [{}]",
                dto.nome());

        Owner owner =
                ownerRepository.findById(dto.ownerId())
                        .orElseThrow(() -> {

                            log.warn(
                                "Tutor [{}] não encontrado",
                                dto.ownerId());

                            return new PetNotFoundException(
                                    "Tutor não encontrado");
                        });

        Pet pet = mapper.createDtoToEntity(dto);

        pet.setOwner(owner);

        Pet salvo = petRepository.save(pet);

        log.info(
                "Pet criado com sucesso. Id [{}], Nome [{}]",
                salvo.getId(),
                salvo.getNome());

        return mapper.entityToResponseDto(salvo);
    }

    public PetResponseDTO atualizar(
            UUID id,
            PetUpdateDTO dto) {

        log.info(
                "Iniciando atualização do pet [{}]",
                id);

        Pet pet =
                petRepository.findById(id)
                        .orElseThrow(() -> {

                            log.warn(
                                "Pet [{}] não encontrado",
                                id);

                            return new PetNotFoundException(
                                    "Pet não encontrado");
                        });

        mapper.updateEntityFromDto(dto, pet);

        Pet atualizado =
                petRepository.save(pet);

        log.info(
                "Pet [{}] atualizado com sucesso",
                atualizado.getId());

        return mapper.entityToResponseDto(atualizado);
    }

    public void deletar(UUID id) {

        log.info(
                "Solicitada exclusão do pet [{}]",
                id);

        Pet pet =
                petRepository.findById(id)
                        .orElseThrow(() -> {

                            log.warn(
                                "Pet [{}] não encontrado",
                                id);

                            return new PetNotFoundException(
                                    "Pet não encontrado");
                        });

        petRepository.delete(pet);

        log.info(
                "Pet [{}] removido com sucesso",
                id);
    }
}