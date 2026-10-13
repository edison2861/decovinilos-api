package com.decovinilos.decovinilos_api.controller;

import com.decovinilos.decovinilos_api.dto.CambioEstadoRequest;
import com.decovinilos.decovinilos_api.dto.CotizacionResponse;
import com.decovinilos.decovinilos_api.service.CotizacionAdminService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/admin/cotizaciones")
@RequiredArgsConstructor
public class AdminCotizacionController {

    private final CotizacionAdminService service;

    @GetMapping
    public List<CotizacionResponse> listar() {
        return service.listar();
    }

    @PatchMapping("/{id}/estado")
    public CotizacionResponse cambiarEstado(@PathVariable Long id,
                                            @Valid @RequestBody CambioEstadoRequest request) {
        return service.cambiarEstado(id, request.estado());
    }
}