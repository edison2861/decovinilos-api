package com.decovinilos.decovinilos_api.dto;

import com.decovinilos.decovinilos_api.model.EstadoCotizacion;
import jakarta.validation.constraints.NotNull;

public record CambioEstadoRequest(@NotNull EstadoCotizacion estado) {}