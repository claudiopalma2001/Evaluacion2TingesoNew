package com.tingeso.ms_recargos_kilometraje.service;
import com.tingeso.ms_recargos_kilometraje.entity.RecargoKilometrajeEntity;
import com.tingeso.ms_recargos_kilometraje.repository.RecargoKilometrajeRepository;
import com.tingeso.ms_recargos_kilometraje.model.Vehiculo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RecargoKilometrajeService {

    @Autowired
    RecargoKilometrajeRepository recargoKilometrajeRepositorio;

    public List<RecargoKilometrajeEntity> getRecargosKilometraje() {
        return (List<RecargoKilometrajeEntity>) recargoKilometrajeRepositorio.findAll();
    }

    public RecargoKilometrajeEntity getRecargoKilometrajeById(Long id) {
        return recargoKilometrajeRepositorio.findById(id).get();
    }

    public List<RecargoKilometrajeEntity> getRecargosKilometrajeByTipoVehiculo(String tipoVehiculo) {
        return recargoKilometrajeRepositorio.findByTipoVehiculo(tipoVehiculo);
    }

    public RecargoKilometrajeEntity saveRecargoKilometraje(RecargoKilometrajeEntity recargoKilometraje) {
        return recargoKilometrajeRepositorio.save(recargoKilometraje);
    }

    public RecargoKilometrajeEntity updateRecargoKilometraje(RecargoKilometrajeEntity recargoKilometraje) {
        return recargoKilometrajeRepositorio.save(recargoKilometraje);
    }

    public boolean deleteRecargoKilometraje(Long id) throws Exception {
        try {
            recargoKilometrajeRepositorio.deleteById(id);
            return true;
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    public float calcularRecargoKilometraje(Vehiculo vehiculo) {
        float kilometraje = vehiculo.getKilometraje();
        if (kilometraje < 0) {
            return 0;
        }
        String tipoVehiculo = vehiculo.getTipoVehiculo();
        Float recargo = recargoKilometrajeRepositorio.findRecargoByKilometrajeAndTipoVehiculo(kilometraje, tipoVehiculo);
        if (recargo != null) {
            return recargo;
        } else {
            return 0;
        }
    }
}
