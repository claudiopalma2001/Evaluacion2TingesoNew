package com.tingeso.ms_historial.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Time;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Detalle {
    private String patente;
    private Long idReparacion;
    private Date fechaIngreso;
    private Time horaIngreso;
    private int montoReparacion;
}
