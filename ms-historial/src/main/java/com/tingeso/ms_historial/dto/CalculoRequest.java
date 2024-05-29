package com.tingeso.ms_historial.dto;

import com.tingeso.ms_historial.model.Detalle;
import com.tingeso.ms_historial.model.Vehiculo;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CalculoRequest {

    private List<Detalle> reparaciones;
    private Vehiculo vehiculo;

}
