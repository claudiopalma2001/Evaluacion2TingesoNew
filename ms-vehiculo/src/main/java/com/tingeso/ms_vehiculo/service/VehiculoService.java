package com.tingeso.ms_vehiculo.service;

import com.tingeso.ms_vehiculo.entity.VehiculoEntity;
import com.tingeso.ms_vehiculo.repository.VehiculoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VehiculoService {

    @Autowired
    VehiculoRepository vehiculosRepositorio;

    public List<VehiculoEntity> getVehiculos() {
        return (List<VehiculoEntity>) vehiculosRepositorio.findAll();
    }

    public VehiculoEntity saveVehiculo(VehiculoEntity vehiculo) {
        return vehiculosRepositorio.save(vehiculo);
    }

    public VehiculoEntity getVehiculoById(Long id) {
        return vehiculosRepositorio.findById(id).get();
    }

    public VehiculoEntity getVehiculoByPatente(String patente) {
        return vehiculosRepositorio.findByPatente(patente);
    }

    public List<VehiculoEntity> getVehiculosByTipoVehiculo(String tipoVehiculo) {
        return vehiculosRepositorio.findByTipoVehiculo(tipoVehiculo);
    }

    public List<VehiculoEntity> getVehiculosByTipoMotor(String tipoMotor) {
        return vehiculosRepositorio.findByTipoMotor(tipoMotor);
    }

    public List<VehiculoEntity> getVehiculosByMarca(String marca) {
        return vehiculosRepositorio.findByMarca(marca);
    }

    public VehiculoEntity updateVehiculo(VehiculoEntity vehiculo) {
        return vehiculosRepositorio.save(vehiculo);
    }

    public boolean deleteVehiculo(Long id) throws Exception {
        try {
            vehiculosRepositorio.deleteById(id);
            return true;
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }
}
