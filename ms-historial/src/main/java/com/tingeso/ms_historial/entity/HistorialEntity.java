package com.tingeso.ms_historial.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import jakarta.persistence.*;

import java.sql.Time;
import java.util.Date;

@Entity
@Table(name = "historial")
@Data
@NoArgsConstructor
@AllArgsConstructor

public class HistorialEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true, nullable = false)
    private Long id;

    private String patente;
    private Date fechaIngreso;
    private Time horaIngreso;
    private float montoTotalReparaciones;
    private float recargos;
    private float descuentos;
    private float iva;
    private float costoTotal;
    private Date fechaSalida;
    private Time horaSalida;
    private Date fechaRecogida;
    private Time horaRecogida;
}