package com.tingeso.ms_recargos_antiguedad.repository;

import com.tingeso.ms_recargos_antiguedad.model.Vehiculo;
import org.springframework.stereotype.Repository;

@Repository
public interface CalculoRecargosAntiguedadRepository {

    public float calcularRecargoPorAntiguedad(Vehiculo vehiculo);
}
