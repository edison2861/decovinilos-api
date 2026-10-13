package com.decovinilos.decovinilos_api.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "producto_especificaciones")
@Getter
@Setter
public class ProductoEspecificacion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "producto_id", nullable = false)
    private Producto producto;

    @Column(nullable = false, length = 50)
    private String label;

    @Column(nullable = false, length = 150)
    private String value;

    @Column(nullable = false)
    private Integer orden = 0;
}