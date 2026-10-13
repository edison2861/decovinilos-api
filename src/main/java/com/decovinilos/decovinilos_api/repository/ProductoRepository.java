package com.decovinilos.decovinilos_api.repository;

import com.decovinilos.decovinilos_api.model.Producto;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ProductoRepository extends JpaRepository<Producto, Integer> {
    long countByEstiloId(Integer estiloId);
    Optional<Producto> findBySlug(String slug);
}