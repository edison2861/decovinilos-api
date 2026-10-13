package com.decovinilos.decovinilos_api.service;

import org.springframework.stereotype.Service;
import com.decovinilos.decovinilos_api.dto.EstiloResponse;
import com.decovinilos.decovinilos_api.mapper.EstiloMapper;
import com.decovinilos.decovinilos_api.repository.EstiloRepository;
import com.decovinilos.decovinilos_api.repository.ProductoRepository;

import java.util.List;

@Service
public class EstiloService {

    private final EstiloRepository estiloRepository;
    private final ProductoRepository productoRepository;

    public EstiloService(EstiloRepository estiloRepository, ProductoRepository productoRepository) {
        this.estiloRepository = estiloRepository;
        this.productoRepository = productoRepository;
    }

    public List<EstiloResponse> listarTodos() {
        return estiloRepository.findAll()
                .stream()
                .map(estilo -> EstiloMapper.toResponse(
                        estilo,
                        productoRepository.countByEstiloId(estilo.getId())
                ))
                .toList();
    }
}