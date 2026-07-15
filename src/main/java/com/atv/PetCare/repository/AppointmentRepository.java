package com.atv.PetCare.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.atv.PetCare.entity.Appointment;

public interface AppointmentRepository extends JpaRepository<Appointment, UUID> {
}