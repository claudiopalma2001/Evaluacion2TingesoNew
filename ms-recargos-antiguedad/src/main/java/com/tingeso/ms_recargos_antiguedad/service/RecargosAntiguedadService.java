package com.tingeso.ms_recargos_antiguedad.service;

import com.tingeso.ms_recargos_antiguedad.entity.RecargosAntiguedadEntity;
import com.tingeso.ms_recargos_antiguedad.model.Vehiculo;
import com.tingeso.ms_recargos_antiguedad.repository.RecargosAntiguedadRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Year;
import java.util.List;

@Service
public class RecargosAntiguedadService {

    @Autowired
    RecargosAntiguedadRepository recargoAntiguedadRepositorio;

    public List<RecargosAntiguedadEntity> getRecargosAntiguedad() {
        return (List<RecargosAntiguedadEntity>) recargoAntiguedadRepositorio.findAll();
    }

    public RecargosAntiguedadEntity getRecargoAntiguedadById(Long id) {
        return recargoAntiguedadRepositorio.findById(id).get();
    }

    public RecargosAntiguedadEntity getRecargoAntiguedadByAntiguedadMin(int antiguedadMin) {
        return recargoAntiguedadRepositorio.findByAntiguedadMin(antiguedadMin);
    }

    public List<RecargosAntiguedadEntity> getRecargosAntiguedadByTipoVehiculo(String tipoVehiculo) {
        return recargoAntiguedadRepositorio.findByTipoVehiculo(tipoVehiculo);
    }

    public RecargosAntiguedadEntity saveRecargoAntiguedad(RecargosAntiguedadEntity recargoAntiguedad) {
        return recargoAntiguedadRepositorio.save(recargoAntiguedad);
    }

    public RecargosAntiguedadEntity updateRecargoAntiguedad(RecargosAntiguedadEntity recargoAntiguedad) {
        return recargoAntiguedadRepositorio.save(recargoAntiguedad);
    }

    public boolean deleteRecargoAntiguedad(Long id) throws Exception {
        try {
            recargoAntiguedadRepositorio.deleteById(id);
            return true;
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }
    public float getRecargoByAntiguedadAndTipoVehiculo(int antiguedad, String tipoVehiculo) {
        return recargoAntiguedadRepositorio.findRecargoByAntiguedadAndTipoVehiculo(antiguedad, tipoVehiculo);
    }

    public float calcularRecargoPorAntiguedad(Vehiculo vehiculo) {
        int anioVehiculo = vehiculo.getAnio();
        int aniooActual = Year.now().getValue();
        int antiguedad = aniooActual - anioVehiculo;
        if (antiguedad > 999 || antiguedad < 0) {
            return 0;
        }
        String tipoVehiculo = vehiculo.getTipoVehiculo();
        float recargo = recargoAntiguedadRepositorio.findRecargoByAntiguedadAndTipoVehiculo(antiguedad, tipoVehiculo);
        System.out.println(recargo);
        if (recargo != 0) {
            return recargo;
        } else {
            return 0;
        }
    }
}
