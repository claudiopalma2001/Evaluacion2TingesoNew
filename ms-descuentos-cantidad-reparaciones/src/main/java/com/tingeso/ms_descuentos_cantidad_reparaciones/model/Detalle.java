package com.tingeso.ms_descuentos_cantidad_reparaciones.model;

import java.sql.Time;
import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

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
