package com.decovinilos.decovinilos_api.service;

import com.decovinilos.decovinilos_api.dto.AuthResponse;
import com.decovinilos.decovinilos_api.dto.LoginRequest;
import com.decovinilos.decovinilos_api.dto.RegistroRequest;
import com.decovinilos.decovinilos_api.dto.UsuarioResponse;
import com.decovinilos.decovinilos_api.model.Rol;
import com.decovinilos.decovinilos_api.model.Usuario;
import com.decovinilos.decovinilos_api.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UsuarioRepository usuarioRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;

    public AuthResponse registrar(RegistroRequest request) {
        String email = request.email().trim().toLowerCase();

        if (usuarioRepository.existsByEmail(email)) {
            throw new ResponseStatusException(HttpStatus.CONFLICT,
                    "Ya existe una cuenta con ese correo");
        }

        Usuario usuario = new Usuario();
        usuario.setNombre(request.nombre().trim());
        usuario.setEmail(email);
        usuario.setPasswordHash(passwordEncoder.encode(request.password()));
        usuario.setRol(Rol.CLIENTE); // el rol NUNCA lo decide el cliente

        usuarioRepository.save(usuario);
        return respuesta(usuario);
    }

    public AuthResponse login(LoginRequest request) {
        String email = request.email().trim().toLowerCase();

        Usuario usuario = usuarioRepository.findByEmail(email)
                .filter(u -> passwordEncoder.matches(request.password(), u.getPasswordHash()))
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.UNAUTHORIZED,
                        "Correo o contraseña incorrectos"));

        return respuesta(usuario);
    }

    public UsuarioResponse perfil(String email) {
        return usuarioRepository.findByEmail(email)
                .map(UsuarioResponse::from)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.UNAUTHORIZED,
                        "Usuario no encontrado"));
    }

    private AuthResponse respuesta(Usuario usuario) {
        return new AuthResponse(jwtService.generarToken(usuario), UsuarioResponse.from(usuario));
    }
}