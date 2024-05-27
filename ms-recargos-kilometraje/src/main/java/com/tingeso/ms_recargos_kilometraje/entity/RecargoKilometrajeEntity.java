package com.tingeso.ms_recargos_kilometraje.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import jakarta.persistence.*;

@Entity
@Table(name = "recargo_kilometraje")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class RecargoKilometrajeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true, nullable = false)
    private Long id;

    private int kilometrajeMin;
    private int kilometrajeMax;
    private String tipoVehiculo;
    private float recargoPorKilometraje;
}
