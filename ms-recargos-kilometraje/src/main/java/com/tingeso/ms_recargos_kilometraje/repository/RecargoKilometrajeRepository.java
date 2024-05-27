package com.tingeso.ms_recargos_kilometraje.repository;

import com.tingeso.ms_recargos_kilometraje.entity.RecargoKilometrajeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RecargoKilometrajeRepository extends JpaRepository<RecargoKilometrajeEntity, Long> {

    public List<RecargoKilometrajeEntity> findByTipoVehiculo(String tipoVehiculo);

    @Query(value = "SELECT recargo_por_kilometraje FROM recargo_kilometraje WHERE :kilometraje BETWEEN recargo_kilometraje.kilometraje_min AND recargo_kilometraje.kilometraje_max AND tipo_vehiculo = :tipoVehiculo", nativeQuery = true)
    public Float findRecargoByKilometrajeAndTipoVehiculo(@Param("kilometraje") float kilometraje, @Param("tipoVehiculo") String tipoVehiculo);

}
