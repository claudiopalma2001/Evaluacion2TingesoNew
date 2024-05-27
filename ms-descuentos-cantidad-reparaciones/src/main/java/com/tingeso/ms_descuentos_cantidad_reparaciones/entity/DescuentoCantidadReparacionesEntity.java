package com.tingeso.ms_descuentos_cantidad_reparaciones.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import jakarta.persistence.*;

@Entity
@Table(name = "descuentosCantidadReparacion")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class DescuentoCantidadReparacionesEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true, nullable = false)
    private Long id;

    private String tipoMotor;
    private int cantidadReparacionesMin;
    private int cantidadReparacionesMax;
    private float descuentoPorNumeroReparaciones;
}
