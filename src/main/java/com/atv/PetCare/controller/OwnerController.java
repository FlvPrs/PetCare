package com.atv.PetCare.controller;

import java.util.UUID;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.atv.PetCare.dto.OwnerCreateDTO;
import com.atv.PetCare.dto.OwnerDetalheDTO;
import com.atv.PetCare.dto.OwnerResponseDTO;
import com.atv.PetCare.service.OwnerService;

@RestController
@RequestMapping("/owners")
public class OwnerController {

    private final OwnerService service;

    public OwnerController(
            OwnerService service) {

        this.service = service;
    }
    
    @GetMapping("/{id}")
    public OwnerDetalheDTO buscarPorId(
            @PathVariable UUID id) {

        return service.buscarPorId(id);
    }

    @PostMapping("/criar")
    public OwnerResponseDTO criar(
            @RequestBody OwnerCreateDTO dto) {

        return service.criar(dto);
    }
}