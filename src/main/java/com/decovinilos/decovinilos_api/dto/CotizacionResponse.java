package com.decovinilos.decovinilos_api.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record CotizacionResponse(
        Long id,
        String productoNombre,
        Integer anchoCm,
        Integer altoCm,
        BigDecimal areaM2,
        Integer cantidadPaneles,
        BigDecimal precioTotal,
        String estado,
        LocalDateTime creadoEn
) {}