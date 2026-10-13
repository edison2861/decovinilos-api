package com.decovinilos.decovinilos_api.dto;

import com.decovinilos.decovinilos_api.model.Usuario;

public record UsuarioResponse(String id, String nombre, String email, String rol) {

    public static UsuarioResponse from(Usuario u) {
        return new UsuarioResponse(
                String.valueOf(u.getId()), u.getNombre(), u.getEmail(), u.getRol().name());
    }
}