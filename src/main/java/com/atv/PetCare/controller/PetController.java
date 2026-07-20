package com.atv.PetCare.controller;

import java.util.List;
import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/pets")
@AllArgsConstructor
public class PetController {

    private final PetService service;

    @PostMapping("/criar")
    @Operation(
            summary = "Criar pet",
            description = "Cadastra um novo pet associado a um tutor")
    public PetResponseDTO criar(
            @Valid @RequestBody PetCreateDTO dto) {

        return service.criar(dto);
    }

    @GetMapping("/listar")
    @Operation(
            summary = "Listar pets",
            description = "Retorna uma lista paginada de todos os pets cadastrados")
    public Page<PetResponseDTO> listar(
            Pageable pageable) {

        return service.listar(pageable);
    }

    @GetMapping("/especie")
    @Operation(
            summary = "Buscar pets por espécie",
            description = "Retorna todos os pets da espécie informada")
    public List<PetResponseDTO> listarPorEspecie(

            @Parameter(
                    description = "Espécie do pet",
                    example = "Cachorro")
            @RequestParam String especie) {

        return service.listarPorEspecie(especie);
    }

    @GetMapping("/idade")
    @Operation(
            summary = "Buscar pets por idade mínima",
            description = "Retorna todos os pets com idade maior que a idade informada")
    public List<PetResponseDTO> listarIdadeMaiorQue(

            @Parameter(
                    description = "Idade mínima do pet",
                    example = "5")
            @RequestParam Integer idade) {

        return service.listarIdadeMaiorQue(idade);
    }

    @PutMapping("/atualizar/{id}")
    @Operation(
            summary = "Atualizar pet",
            description = "Atualiza os dados de um pet existente através do ID")
    public PetResponseDTO atualizar(

            @Parameter(
                    description = "Identificador único do pet",
                    example = "38fe6f66-f07d-4a2d-a7d3-02586d195c44")
            @PathVariable UUID id,

            @RequestBody PetUpdateDTO dto) {

        return service.atualizar(id, dto);
    }

    @DeleteMapping("/deletar/{id}")
    @Operation(
            summary = "Excluir pet",
            description = "Remove um pet do sistema através do ID")
    public ResponseEntity<Void> deletar(

            @Parameter(
                    description = "Identificador único do pet",
                    example = "38fe6f66-f07d-4a2d-a7d3-02586d195c44")
            @PathVariable UUID id) {

        service.deletar(id);

        return ResponseEntity.noContent().build();
    }
}