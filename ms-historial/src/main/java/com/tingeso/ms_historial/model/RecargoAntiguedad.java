package com.tingeso.ms_historial.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Time;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RecargoAntiguedad {

    private int antiguedadMin;
    private int antiguedadMax;
    private String tipoVehiculo;
    private float recargoPorAntiguedad;
}
