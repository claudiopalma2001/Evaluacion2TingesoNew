package com.tingeso.ms_descuentos_cantidad_reparaciones.dto;

import com.tingeso.ms_descuentos_cantidad_reparaciones.model.Detalle;
import com.tingeso.ms_descuentos_cantidad_reparaciones.model.Vehiculo;
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
