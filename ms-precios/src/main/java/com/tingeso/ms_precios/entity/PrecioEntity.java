package com.tingeso.ms_precios.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import jakarta.persistence.*;

@Entity
@Table(name = "precios")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PrecioEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true, nullable = false)
    private Long id;

    private Long idReparacion;
    private String tipoDeMotor;
    private int precio;

}
