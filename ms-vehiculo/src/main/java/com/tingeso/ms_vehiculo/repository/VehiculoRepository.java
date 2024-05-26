package com.tingeso.ms_vehiculo.repository;

import com.tingeso.ms_vehiculo.entity.VehiculoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VehiculoRepository extends JpaRepository<VehiculoEntity, Long>{

    public VehiculoEntity findByPatente(String patente);
    public List<VehiculoEntity> findByTipoVehiculo(String tipoVehiculo);
    public List<VehiculoEntity> findByTipoMotor(String tipoMotor);
    public List<VehiculoEntity> findByMarca(String marca);
}