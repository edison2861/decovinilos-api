package com.decovinilos.decovinilos_api.mapper;

import com.decovinilos.decovinilos_api.dto.EstiloResponse;
import com.decovinilos.decovinilos_api.model.Estilo;

public class EstiloMapper {

    private EstiloMapper() {
    }

    public static EstiloResponse toResponse(Estilo estilo, long articulos) {
        return new EstiloResponse(
                estilo.getSlug(),
                estilo.getNombre(),
                estilo.getEtiqueta(),
                estilo.getDescripcion(),
                articulos
        );
    }
}