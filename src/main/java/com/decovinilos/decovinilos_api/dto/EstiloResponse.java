package com.decovinilos.decovinilos_api.dto;

public record EstiloResponse(
        String slug,
        String nombre,
        String etiqueta,
        String descripcion,
        long articulos
) {}