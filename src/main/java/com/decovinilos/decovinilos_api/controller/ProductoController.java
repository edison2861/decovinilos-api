package com.decovinilos.decovinilos_api.controller;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.decovinilos.decovinilos_api.dto.ProductoResponse;
import com.decovinilos.decovinilos_api.service.ProductoService;
import org.springframework.web.bind.annotation.PathVariable;
import java.util.List;

@RestController
@RequestMapping("/api/productos")
@CrossOrigin(origins = "http://localhost:3000")
public class ProductoController {

    private final ProductoService productoService;

    public ProductoController(ProductoService productoService) {
        this.productoService = productoService;
    }

    @GetMapping
    public List<ProductoResponse> listar() {
        return productoService.listarTodos();
    }

    @GetMapping("/{slug}")
    public ResponseEntity<ProductoResponse> obtenerPorSlug(@PathVariable String slug) {
        return productoService.obtenerPorSlug(slug)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }
}