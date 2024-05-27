package com.tingeso.ms_recargos_antiguedad.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import jakarta.persistence.*;

@Entity
@Table(name = "recargo_antiguedad")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class RecargosAntiguedadEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true, nullable = false)
    private Long id;

    private int antiguedadMin;
    private int antiguedadMax;
    private String tipoVehiculo;
    private float recargoPorAntiguedad;
}
