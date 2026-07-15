package com.atv.PetCare.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.atv.PetCare.dto.AppointmentCreateDTO;
import com.atv.PetCare.dto.AppointmentResponseDTO;
import com.atv.PetCare.service.AppointmentService;

@RestController
@RequestMapping("/appointments")
public class AppointmentController {

    private final AppointmentService service;

    public AppointmentController(
            AppointmentService service) {

        this.service = service;
    }

    @PostMapping("/criar")
    public AppointmentResponseDTO criar(
            @RequestBody AppointmentCreateDTO dto) {

        return service.criar(dto);
    }
}