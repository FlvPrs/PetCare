package com.atv.PetCare.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.atv.PetCare.dto.AppointmentCreateDTO;
import com.atv.PetCare.dto.AppointmentResponseDTO;
import com.atv.PetCare.entity.Appointment;
import com.atv.PetCare.entity.Pet;
import com.atv.PetCare.mapper.AppointmentMapper;
import com.atv.PetCare.repository.AppointmentRepository;
import com.atv.PetCare.repository.PetRepository;

@Service
public class AppointmentService {

    private static final Logger log =
            LoggerFactory.getLogger(
                    AppointmentService.class);

    private final AppointmentRepository repository;
    private final PetRepository petRepository;
    private final AppointmentMapper mapper;

    public AppointmentService(
            AppointmentRepository repository,
            PetRepository petRepository,
            AppointmentMapper mapper) {

        this.repository = repository;
        this.petRepository = petRepository;
        this.mapper = mapper;
    }

    public AppointmentResponseDTO criar(
            AppointmentCreateDTO dto) {

        log.info(
            "Criando consulta para pet {}",
            dto.petId());

        Pet pet = petRepository
                .findById(dto.petId())
                .orElseThrow(() ->
                        new RuntimeException(
                                "Pet não encontrado"));

        Appointment appointment =
                mapper.createDtoToEntity(dto);

        appointment.setPet(pet);

        Appointment salvo =
                repository.save(appointment);

        log.info(
            "Consulta {} criada",
            salvo.getId());

        return mapper.entityToResponseDto(salvo);
    }
}