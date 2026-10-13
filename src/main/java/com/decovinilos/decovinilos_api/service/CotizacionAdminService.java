package com.decovinilos.decovinilos_api.service;

import com.decovinilos.decovinilos_api.dto.CotizacionResponse;
import com.decovinilos.decovinilos_api.model.*;
import com.decovinilos.decovinilos_api.repository.CotizacionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CotizacionAdminService {

    private final CotizacionRepository cotizacionRepository;

    @Transactional(readOnly = true)
    public List<CotizacionResponse> listar() {
        return cotizacionRepository
                .findAll(Sort.by(Sort.Direction.DESC, "creadoEn"))
                .stream()
                .map(this::aResponse)
                .toList();
    }

    @Transactional
    public CotizacionResponse cambiarEstado(Long id, EstadoCotizacion estado) {
        Cotizacion cotizacion = cotizacionRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "Cotización no encontrada"));

        cotizacion.setEstado(estado);
        return aResponse(cotizacionRepository.save(cotizacion));
    }

    private CotizacionResponse aResponse(Cotizacion c) {
        return new CotizacionResponse(
                c.getId(),
                c.getProducto().getNombre(),
                c.getAnchoCm(),
                c.getAltoCm(),
                c.getAreaM2(),
                c.getCantidadPaneles(),
                c.getPrecioTotal(),
                c.getEstado().name(),
                c.getCreadoEn());
    }
}