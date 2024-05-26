package com.tingeso.ms_historial.repository;

import com.tingeso.ms_historial.entity.HistorialEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HistorialRepository extends JpaRepository <HistorialEntity, Long>{


    public HistorialEntity findByPatente(String patente);

    public List<HistorialEntity> findAllByPatente(String patente);
}
