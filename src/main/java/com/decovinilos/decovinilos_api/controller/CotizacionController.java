package com.decovinilos.decovinilos_api.controller;

import com.decovinilos.decovinilos_api.dto.CotizacionRequest;
import com.decovinilos.decovinilos_api.dto.CotizacionResponse;
import com.decovinilos.decovinilos_api.service.CotizacionService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/cotizaciones")
@CrossOrigin(origins = "http://localhost:3000")
public class CotizacionController {

    private final CotizacionService cotizacionService;

    public CotizacionController(CotizacionService cotizacionService) {
        this.cotizacionService = cotizacionService;
    }

    @PostMapping
    public ResponseEntity<CotizacionResponse> crear(@RequestBody CotizacionRequest request) {
        CotizacionResponse response = cotizacionService.crear(request);
        return ResponseEntity.status(201).body(response);
    }
}