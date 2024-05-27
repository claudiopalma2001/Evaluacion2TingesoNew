package com.tingeso.ms_descuentos_bonos.repository;

import com.tingeso.ms_descuentos_bonos.entity.DescuentoBonosEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface DescuentoBonosRepository extends JpaRepository<DescuentoBonosEntity, Long> {

    public Optional<DescuentoBonosEntity> findByMarcaVehiculo(String marcaVehiculo);

}
