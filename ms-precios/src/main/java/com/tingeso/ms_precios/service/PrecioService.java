package com.tingeso.ms_precios.service;

import com.tingeso.ms_precios.entity.PrecioEntity;
import com.tingeso.ms_precios.repository.PrecioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PrecioService {

    @Autowired
    PrecioRepository preciosReparacionRepositorio;

    public List<PrecioEntity> getPreciosReparacion() {
        return (List<PrecioEntity>) preciosReparacionRepositorio.findAll();
    }

    public PrecioEntity getPreciosReparacionById(Long id) {
        return preciosReparacionRepositorio.findById(id).get();
    }

    public List<PrecioEntity> getPreciosReparacionByTipoDeMotor(String tipoMotor) {
        return preciosReparacionRepositorio.findByTipoDeMotor(tipoMotor);
    }

    public PrecioEntity savePreciosReparacion(PrecioEntity preciosReparacion) {
        return preciosReparacionRepositorio.save(preciosReparacion);
    }

    public PrecioEntity updatePreciosReparacion(PrecioEntity preciosReparacion) {
        return preciosReparacionRepositorio.save(preciosReparacion);
    }

    public boolean deletePreciosReparacion(Long id) throws Exception {
        try {
            preciosReparacionRepositorio.deleteById(id);
            return true;
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }
}
