package com.tingeso.ms_recargos_antiguedad.repository;

import com.tingeso.ms_recargos_antiguedad.entity.RecargosAntiguedadEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RecargosAntiguedadRepository extends JpaRepository<RecargosAntiguedadEntity, Long>{

    public RecargosAntiguedadEntity findByAntiguedadMin(int antiguedadMIN);
    public List<RecargosAntiguedadEntity> findByTipoVehiculo(String tipoVehiculo);

    @Query(value = "SELECT recargo_por_antiguedad FROM recargo_antiguedad WHERE :antiguedad BETWEEN recargo_antiguedad.antiguedad_min AND recargo_antiguedad.antiguedad_max AND tipo_vehiculo = :tipoVehiculo", nativeQuery = true)
    public float findRecargoByAntiguedadAndTipoVehiculo(@Param("antiguedad") int antiguedad, @Param("tipoVehiculo") String tipoVehiculo);

}
