package com.tingeso.ms_historial.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Time;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DescuentoBonos {
    private String marcaVehiculo;
    private int cantidadDescuentos;
    private int montoDescuentos;
}
