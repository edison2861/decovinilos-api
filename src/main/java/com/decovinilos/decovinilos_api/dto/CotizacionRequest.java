package com.decovinilos.decovinilos_api.dto;

public record CotizacionRequest(
        String productoSlug,
        Integer anchoCm,
        Integer altoCm,
        Boolean incluyeMargen
) {}