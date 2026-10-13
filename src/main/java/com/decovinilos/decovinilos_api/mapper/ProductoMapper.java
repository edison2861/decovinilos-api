package com.decovinilos.decovinilos_api.mapper;

import com.decovinilos.decovinilos_api.dto.AcabadoResponse;
import com.decovinilos.decovinilos_api.dto.EspecificacionResponse;
import com.decovinilos.decovinilos_api.dto.ProductoResponse;
import com.decovinilos.decovinilos_api.model.Producto;

import java.util.List;

public class ProductoMapper {

    private ProductoMapper() {
    }

    public static ProductoResponse toResponse(Producto producto) {
        List<EspecificacionResponse> especificaciones = producto.getEspecificaciones().stream()
                .map(spec -> new EspecificacionResponse(spec.getLabel(), spec.getValue()))
                .toList();

        List<AcabadoResponse> acabados = producto.getAcabados().stream()
                .map(acabado -> new AcabadoResponse(
                        acabado.getNombre(),
                        acabado.getDescripcion(),
                        acabado.getExtraPorM2()
                ))
                .toList();

        return new ProductoResponse(
                producto.getSlug(),
                producto.getNombre(),
                producto.getEstilo().getSlug(),
                producto.getDescripcion(),
                producto.getDimensiones(),
                producto.getAcabado(),
                producto.getPrecio(),
                producto.getPrecioM2(),
                producto.getRating(),
                producto.getReviewCount(),
                especificaciones,
                acabados
        );
    }
}