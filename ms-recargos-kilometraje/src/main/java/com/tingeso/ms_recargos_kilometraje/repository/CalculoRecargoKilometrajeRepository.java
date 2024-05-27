package com.tingeso.ms_recargos_kilometraje.repository;

import com.tingeso.ms_recargos_kilometraje.model.Vehiculo;
import org.springframework.stereotype.Repository;

@Repository
public interface CalculoRecargoKilometrajeRepository {

    public float calcularRecargoKilometraje(Vehiculo vehiculo);
}
