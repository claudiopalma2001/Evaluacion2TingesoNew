package com.tingeso.ms_descuentos_bonos.repository;

import com.tingeso.ms_descuentos_bonos.model.Vehiculo;
import org.springframework.stereotype.Repository;

@Repository
public interface CalculoDescuentoBonosRepository {

    public float calcularDescuentoBono(Vehiculo vehiculo);
}
