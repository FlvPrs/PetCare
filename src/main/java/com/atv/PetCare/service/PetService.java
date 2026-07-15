package com.atv.PetCare.service;

import java.util.List;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.atv.PetCare.dto.PetCreateDTO;
import com.atv.PetCare.dto.PetResponseDTO;
import com.atv.PetCare.dto.PetUpdateDTO;
import com.atv.PetCare.entity.Owner;
import com.atv.PetCare.entity.Pet;
import com.atv.PetCare.mapper.PetMapper;
import com.atv.PetCare.repository.OwnerRepository;
import com.atv.PetCare.repository.PetRepository;

@Service
public class PetService {
	
	private final PetMapper mapper;
	
	private static final Logger log =
            LoggerFactory.getLogger(PetService.class);

    private final PetRepository petRepository;
    
    private final OwnerRepository ownerRepository; 

    public PetService(PetRepository petRepository,PetMapper mapper,OwnerRepository ownerRepository) {
    	this.ownerRepository = ownerRepository;
    	this.mapper			 = mapper;
        this.petRepository 	 = petRepository;
    }

    public List<Pet> listar() {
    	log.info("Iniciando busca de todos os pets");

        List<Pet> pets = petRepository.findAll();

        log.info("Foram encontrados {} pets", pets.size());

        return pets;
    }
    
    public List<Pet> listarPorEspecie(String especie) {
        return petRepository.findByEspecie(especie);
    }
    
    public List<Pet> listarIdadeMaiorQue(Integer idade) {
        return petRepository.findByIdadeGreaterThan(idade);
    }

	public PetResponseDTO criar(PetCreateDTO dto) {
		
		Owner owner =
	            ownerRepository.findById(dto.ownerId())
	                .orElseThrow();
		
		Pet pet = mapper.createDtoToEntity(dto);
		
		pet.setOwner(owner);

	    Pet salvo = petRepository.save(pet);

	    return mapper.entityToResponseDto(salvo);
	    
	}

	public PetResponseDTO  atualizar(UUID id, PetUpdateDTO dto) {
		
		Pet pet = petRepository.findById(id)
	            .orElseThrow();

	    mapper.updateEntityFromDto(dto, pet);

	    Pet atualizado = petRepository.save(pet);

	    return mapper.entityToResponseDto(atualizado);
	}
	
	public void deletar(UUID id) {

	    log.info("Solicitada exclusão do pet {}", id);

	    Pet pet = petRepository.findById(id)
	            .orElseThrow(() -> {
	                log.warn("Pet {} não encontrado", id);
	                return new RuntimeException("Pet não encontrado");
	            });

	    petRepository.delete(pet);

	    log.info("Pet {} removido com sucesso", id);
	}
}