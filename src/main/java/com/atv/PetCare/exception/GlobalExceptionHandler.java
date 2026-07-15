package com.atv.PetCare.exception;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.atv.PetCare.dto.ErrorResponseDTO;

import jakarta.servlet.http.HttpServletRequest;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(
            PetNotFoundException.class)
    public ResponseEntity<ErrorResponseDTO>
    handlePetNotFound(
            PetNotFoundException ex,
            HttpServletRequest request) {

        ErrorResponseDTO error =
                new ErrorResponseDTO(
                        LocalDateTime.now(),
                        HttpStatus.NOT_FOUND.value(),
                        "Pet Not Found",
                        ex.getMessage(),
                        request.getRequestURI());

        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(error);
    }
    
    @ExceptionHandler(
            OwnerNotFoundException.class)
    public ResponseEntity<ErrorResponseDTO>
    handleOwnerNotFound(
            OwnerNotFoundException ex,
            HttpServletRequest request) {

        ErrorResponseDTO error =
                new ErrorResponseDTO(
                        LocalDateTime.now(),
                        HttpStatus.NOT_FOUND.value(),
                        "Owner Not Found",
                        ex.getMessage(),
                        request.getRequestURI());

        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(error);
    }
    
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponseDTO>
    handleGeneric(
            Exception ex,
            HttpServletRequest request) {

        ErrorResponseDTO error =
                new ErrorResponseDTO(
                        LocalDateTime.now(),
                        500,
                        "Internal Server Error",
                        ex.getMessage(),
                        request.getRequestURI());

        return ResponseEntity
                .status(500)
                .body(error);
    }
}