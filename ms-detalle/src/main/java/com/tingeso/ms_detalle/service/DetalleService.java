package com.tingeso.ms_detalle.service;

import com.tingeso.ms_detalle.entity.DetalleEntity;
import com.tingeso.ms_detalle.repository.DetalleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DetalleService {

    @Autowired
    DetalleRepository detalleRepository;

    public List<DetalleEntity> getDetalle() {
        return (List<DetalleEntity>) detalleRepository.findAll();
    }

    public DetalleEntity getDetalleById(Long id) {
        return detalleRepository.findById(id).get();
    }

    public DetalleEntity getDetalleByIdReparacion(Long idReparacion) {
        return detalleRepository.findByIdReparacion(idReparacion);
    }

    public DetalleEntity getDetalleByPatente(String patente) {
        return detalleRepository.findByPatente(patente);
    }

    public List<DetalleEntity> getDetalleByPatentes(String patente) {
        return detalleRepository.findAllByPatente(patente);
    }


    public DetalleEntity saveDetalle(DetalleEntity historialReparaciones) {
        return detalleRepository.save(historialReparaciones);
    }

    public DetalleEntity updateDetalle(DetalleEntity historialReparaciones) {
        return detalleRepository.save(historialReparaciones);
    }

    public boolean deleteDetalle(Long id) throws Exception {
        try {
            detalleRepository.deleteById(id);
            return true;
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

}
