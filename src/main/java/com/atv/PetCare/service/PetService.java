package com.atv.PetCare.service;

import java.util.List;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.atv.PetCare.dto.PetCreateDTO;
import com.atv.PetCare.dto.PetResponseDTO;
import com.atv.PetCare.dto.PetUpdateDTO;
import com.atv.PetCare.entity.Pet;
import com.atv.PetCare.mapper.PetMapper;
import com.atv.PetCare.repository.PetRepository;

@Service
public class PetService {
	
	private final PetMapper mapper;
	
	private static final Logger log =
            LoggerFactory.getLogger(PetService.class);

    private final PetRepository repository;

    public PetService(PetRepository repository,PetMapper mapper) {
    	this.mapper		= mapper;
        this.repository = repository;
    }

    public List<Pet> listar() {
    	log.info("Iniciando busca de todos os pets");

        List<Pet> pets = repository.findAll();

        log.info("Foram encontrados {} pets", pets.size());

        return pets;
    }
    
    public List<Pet> listarPorEspecie(String especie) {
        return repository.findByEspecie(especie);
    }
    
    public List<Pet> listarIdadeMaiorQue(Integer idade) {
        return repository.findByIdadeGreaterThan(idade);
    }

	public PetResponseDTO criar(PetCreateDTO dto) {
		
		Pet pet = mapper.createDtoToEntity(dto);

	    Pet salvo = repository.save(pet);

	    return mapper.entityToResponseDto(salvo);
	    
	}

	public PetResponseDTO  atualizar(UUID id, PetUpdateDTO dto) {
		
		Pet pet = repository.findById(id)
	            .orElseThrow();

	    mapper.updateEntityFromDto(dto, pet);

	    Pet atualizado = repository.save(pet);

	    return mapper.entityToResponseDto(atualizado);
	}
	
	public void deletar(UUID id) {

	    log.info("Solicitada exclusão do pet {}", id);

	    Pet pet = repository.findById(id)
	            .orElseThrow(() -> {
	                log.warn("Pet {} não encontrado", id);
	                return new RuntimeException("Pet não encontrado");
	            });

	    repository.delete(pet);

	    log.info("Pet {} removido com sucesso", id);
	}
}