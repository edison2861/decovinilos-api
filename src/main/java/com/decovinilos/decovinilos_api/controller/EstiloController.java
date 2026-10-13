package com.decovinilos.decovinilos_api.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.decovinilos.decovinilos_api.dto.EstiloResponse;
import com.decovinilos.decovinilos_api.service.EstiloService;

import java.util.List;

@RestController
@RequestMapping("/api/estilos")
@CrossOrigin(origins = "http://localhost:3000")
public class EstiloController {

    private final EstiloService estiloService;

    public EstiloController(EstiloService estiloService) {
        this.estiloService = estiloService;
    }

    @GetMapping
    public List<EstiloResponse> listar() {
        return estiloService.listarTodos();
    }
}