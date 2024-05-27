package com.tingeso.ms_descuentos_cantidad_reparaciones.repositories;

import com.tingeso.ms_descuentos_cantidad_reparaciones.model.Detalle;
import com.tingeso.ms_descuentos_cantidad_reparaciones.model.Vehiculo;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;
@Repository
public interface CalculoDescuentoCantidadReparacionesRepository {

    public boolean calcularFechaHaceUnAnio(Date date);

    public float calcularDescuentoCantidadReparacion(List<Detalle> reparaciones, Vehiculo vehiculo);

}
