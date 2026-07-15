package com.atv.PetCare.controller;

import java.util.List;
import java.util.UUID;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.atv.PetCare.dto.PetCreateDTO;
import com.atv.PetCare.dto.PetResponseDTO;
import com.atv.PetCare.dto.PetUpdateDTO;
import com.atv.PetCare.service.PetService;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/pets")
@AllArgsConstructor
public class PetController {

    private final PetService service;
    
    @PostMapping("/criar")
    public PetResponseDTO criar(@Valid @RequestBody PetCreateDTO dto) {
        return service.criar(dto);
    }

    @GetMapping("/listar")
    public List<PetResponseDTO> listar() {
        return service.listar();
    }
    
    @GetMapping("/especie")
    public List<PetResponseDTO> listarPorEspecie(
            @RequestParam String especie) {

        return service.listarPorEspecie(especie);
    }

    @GetMapping("/idade")
    public List<PetResponseDTO> listarIdadeMaiorQue(
            @RequestParam Integer idade) {

        return service.listarIdadeMaiorQue(idade);
    }      
    
    @PutMapping("/atualizar/{id}")
    public PetResponseDTO atualizar(
            @PathVariable UUID id,
            @RequestBody PetUpdateDTO dto) {

        return service.atualizar(id, dto);
    }
    
    @DeleteMapping("/deletar/{id}")
    public ResponseEntity<Void> deletar(@PathVariable UUID id) {

        service.deletar(id);

        return ResponseEntity.noContent().build();
    }
}