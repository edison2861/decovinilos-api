package com.decovinilos.decovinilos_api.service;

import org.springframework.stereotype.Service;
import com.decovinilos.decovinilos_api.dto.ProductoResponse;
import com.decovinilos.decovinilos_api.mapper.ProductoMapper;
import com.decovinilos.decovinilos_api.repository.ProductoRepository;
import java.util.Optional;
import java.util.List;

@Service
public class ProductoService {

    private final ProductoRepository productoRepository;

    public ProductoService(ProductoRepository productoRepository) {
        this.productoRepository = productoRepository;
    }

    public List<ProductoResponse> listarTodos() {
        return productoRepository.findAll()
                .stream()
                .map(ProductoMapper::toResponse)
                .toList();
    }

    public Optional<ProductoResponse> obtenerPorSlug(String slug) {
    return productoRepository.findBySlug(slug)
            .map(ProductoMapper::toResponse);
    }
}