package com.tingeso.ms_historial.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Vehiculo {

    private String patente;
    private String marca;
    private String modelo;
    private String tipoVehiculo;
    private int anio;
    private String tipoMotor;
    private float kilometraje;

}
