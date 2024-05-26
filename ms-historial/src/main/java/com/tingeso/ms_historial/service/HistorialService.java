package com.tingeso.ms_historial.service;

import com.tingeso.ms_historial.entity.HistorialEntity;
import com.tingeso.ms_historial.repository.HistorialRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HistorialService {

    @Autowired
    HistorialRepository historialRepository;

    public List<HistorialEntity> getHistorialReparaciones() {
        return (List<HistorialEntity>) historialRepository.findAll();
    }

    public HistorialEntity getHistorialReparacionesById(Long id) {
        return historialRepository.findById(id).get();
    }

    public HistorialEntity getHistorialReparacionesByPatente(String patente) {
        return historialRepository.findByPatente(patente);
    }

    public List<HistorialEntity> getHistorialReparacionesByPatentes(String patente) {
        return historialRepository.findAllByPatente(patente);
    }


    public HistorialEntity saveHistorialReparaciones(HistorialEntity historialReparaciones) {
        return historialRepository.save(historialReparaciones);
    }

    public HistorialEntity updateHistorialReparaciones(HistorialEntity historialReparaciones) {
        return historialRepository.save(historialReparaciones);
    }

    public boolean deleteHistorialReparaciones(Long id) throws Exception {
        try {
            historialRepository.deleteById(id);
            return true;
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }
}
