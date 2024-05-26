package com.tingeso.ms_vehiculo.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import jakarta.persistence.*;

@Entity
@Table(name = "vehiculos")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class VehiculoEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true, nullable = false)
    private Long id;

    private String patente;
    private String marca;
    private String modelo;
    private String tipoVehiculo;
    private int anio;
    private String tipoMotor;
    private float kilometraje;
}
