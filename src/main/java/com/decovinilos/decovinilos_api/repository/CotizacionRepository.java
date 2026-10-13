package com.decovinilos.decovinilos_api.repository;

import com.decovinilos.decovinilos_api.model.Cotizacion;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CotizacionRepository extends JpaRepository<Cotizacion, Long> {
}