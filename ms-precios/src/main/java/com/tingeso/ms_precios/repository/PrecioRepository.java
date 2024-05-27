package com.tingeso.ms_precios.repository;
import com.tingeso.ms_precios.entity.PrecioEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface PrecioRepository extends JpaRepository<PrecioEntity, Long>{

    public List<PrecioEntity> findByTipoDeMotor(String tipoDeMotor);
}
