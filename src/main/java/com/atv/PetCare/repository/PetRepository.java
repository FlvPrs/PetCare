package com.atv.PetCare.repository;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.atv.PetCare.entity.Pet;

@Repository
public interface PetRepository
    extends JpaRepository<Pet, UUID> {
	
	List<Pet> findByIdadeGreaterThan(Integer idade);
	
	List<Pet> findByEspecie(String especie);
}