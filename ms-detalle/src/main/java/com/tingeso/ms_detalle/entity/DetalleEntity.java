package com.tingeso.ms_detalle.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Time;
import java.util.Date;

@Entity
@Table(name = "detalles")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class DetalleEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true, nullable = false)
    private Long id;

    private String patente;
    private Long idReparacion;
    private Date fechaIngreso;
    private Time horaIngreso;
    private int montoReparacion;
}
