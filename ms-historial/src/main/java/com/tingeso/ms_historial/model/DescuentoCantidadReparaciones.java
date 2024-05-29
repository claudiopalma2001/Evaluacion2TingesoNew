package com.tingeso.ms_historial.model;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Time;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DescuentoCantidadReparaciones {
    private String tipoMotor;
    private int cantidadReparacionesMin;
    private int cantidadReparacionesMax;
    private float descuentoPorNumeroReparaciones;
}

