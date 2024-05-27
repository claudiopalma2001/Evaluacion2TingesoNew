package com.tingeso.ms_descuentos_cantidad_reparaciones.repositories;

import com.tingeso.ms_descuentos_cantidad_reparaciones.entity.DescuentoCantidadReparacionesEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DescuentoCantidadReparacionesRepository extends JpaRepository<DescuentoCantidadReparacionesEntity, Long>{

    public List<DescuentoCantidadReparacionesEntity> findByTipoMotor(String tipoMotor);

    public List<DescuentoCantidadReparacionesEntity> findByCantidadReparacionesMin(int cantidadReparaciones);

    @Query(value = "SELECT * FROM descuentos_cantidad_reparaciones WHERE descuentos_cantidad_reparaciones.tipo_motor = :tipoMotor AND descuentos_cantidad_reparaciones.cantidad_reparaciones_min <= :numReparaciones AND descuentos_cantidad_reparaciones.cantidad_reparaciones_max >= :numReparaciones", nativeQuery = true)
    DescuentoCantidadReparacionesEntity searchByCantidadReparacionesMinBetween(@Param("tipoMotor") String tipoMotor, @Param("numReparaciones") int numReparaciones);

}
