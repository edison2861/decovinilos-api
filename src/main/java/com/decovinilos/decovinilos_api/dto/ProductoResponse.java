package com.decovinilos.decovinilos_api.dto;
import java.math.BigDecimal;
import java.util.List;

public record ProductoResponse(
        String slug,
        String nombre,
        String estilo,
        String descripcion,
        String dimensiones,
        String acabado,
        BigDecimal precio,
        BigDecimal precioM2,
        BigDecimal rating,
        Integer reviewCount,
        List<EspecificacionResponse> especificaciones,
        List<AcabadoResponse> acabados
) {}