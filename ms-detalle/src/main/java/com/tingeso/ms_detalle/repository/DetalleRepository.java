package com.tingeso.ms_detalle.repository;

import org.springframework.stereotype.Repository;
import com.tingeso.ms_detalle.entity.DetalleEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

@Repository
public interface DetalleRepository extends JpaRepository<DetalleEntity, Long> {

    public DetalleEntity findByIdReparacion(Long idReparacion);

    public DetalleEntity findByPatente(String patente);

    public List<DetalleEntity> findAllByPatente(String patente);
}
