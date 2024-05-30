package com.tingeso.ms_precios.repository;
import com.tingeso.ms_precios.entity.PrecioEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface PrecioRepository extends JpaRepository<PrecioEntity, Long>{

    public List<PrecioEntity> findByTipoDeMotor(String tipoDeMotor);

    @Query(value = "SELECT * FROM precios WHERE id_reparacion = :idReparacion AND tipo_de_motor = :tipoDeMotor", nativeQuery = true)
    PrecioEntity findByIdReparacionAndTipoDeMotor(@Param("idReparacion") Long idReparacion, @Param("tipoDeMotor") String tipoDeMotor);
}
