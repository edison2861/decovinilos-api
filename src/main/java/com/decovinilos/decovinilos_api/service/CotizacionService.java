package com.decovinilos.decovinilos_api.service;

import com.decovinilos.decovinilos_api.dto.CotizacionRequest;
import com.decovinilos.decovinilos_api.dto.CotizacionResponse;
import com.decovinilos.decovinilos_api.model.Cotizacion;
import com.decovinilos.decovinilos_api.model.EstadoCotizacion;
import com.decovinilos.decovinilos_api.model.Producto;
import com.decovinilos.decovinilos_api.repository.CotizacionRepository;
import com.decovinilos.decovinilos_api.repository.ProductoRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class CotizacionService {

    private static final BigDecimal PRECIO_M2_POR_DEFECTO = BigDecimal.valueOf(32.5);

    private final CotizacionRepository cotizacionRepository;
    private final ProductoRepository productoRepository;

    public CotizacionService(CotizacionRepository cotizacionRepository, ProductoRepository productoRepository) {
        this.cotizacionRepository = cotizacionRepository;
        this.productoRepository = productoRepository;
    }

    public CotizacionResponse crear(CotizacionRequest request) {
        Producto producto = productoRepository.findBySlug(request.productoSlug())
                .orElseThrow(() -> new IllegalArgumentException("Producto no encontrado: " + request.productoSlug()));

        BigDecimal precioM2 = producto.getPrecioM2() != null ? producto.getPrecioM2() : PRECIO_M2_POR_DEFECTO;

        CotizacionCalculator.Resultado resultado = CotizacionCalculator.calcular(
                request.anchoCm(),
                request.altoCm(),
                Boolean.TRUE.equals(request.incluyeMargen()),
                precioM2
        );

        Cotizacion cotizacion = new Cotizacion();
        cotizacion.setProducto(producto);
        cotizacion.setAnchoCm(request.anchoCm());
        cotizacion.setAltoCm(request.altoCm());
        cotizacion.setIncluyeMargen(Boolean.TRUE.equals(request.incluyeMargen()));
        cotizacion.setAreaM2(resultado.areaM2());
        cotizacion.setCantidadPaneles(resultado.cantidadPaneles());
        cotizacion.setPrecioTotal(resultado.precioTotal());
        cotizacion.setEstado(EstadoCotizacion.PENDIENTE);

        Cotizacion guardada = cotizacionRepository.save(cotizacion);

        return new CotizacionResponse(
                guardada.getId(),
                producto.getNombre(),
                guardada.getAnchoCm(),
                guardada.getAltoCm(),
                guardada.getAreaM2(),
                guardada.getCantidadPaneles(),
                guardada.getPrecioTotal(),
                guardada.getEstado().name(),
                guardada.getCreadoEn()
        );
    }
}