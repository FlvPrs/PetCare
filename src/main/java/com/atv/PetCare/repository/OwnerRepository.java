package com.atv.PetCare.repository;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.atv.PetCare.entity.Owner;

public interface OwnerRepository
        extends JpaRepository<Owner, UUID> {
	
	@Query("""
		    SELECT o
		    FROM Owner o
		    LEFT JOIN FETCH o.pets
		    WHERE o.id = :id
		""")
		Optional<Owner> buscarComPets(UUID id);
}