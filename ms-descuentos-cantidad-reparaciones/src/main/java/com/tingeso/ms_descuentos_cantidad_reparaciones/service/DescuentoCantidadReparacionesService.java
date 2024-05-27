package com.tingeso.ms_descuentos_cantidad_reparaciones.service;

import com.tingeso.ms_descuentos_cantidad_reparaciones.model.Detalle;
import com.tingeso.ms_descuentos_cantidad_reparaciones.model.Vehiculo;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;
import java.util.List;
import com.tingeso.ms_descuentos_cantidad_reparaciones.entity.DescuentoCantidadReparacionesEntity;
import com.tingeso.ms_descuentos_cantidad_reparaciones.repositories.DescuentoCantidadReparacionesRepository;
import org.springframework.stereotype.Service;

@Service
public class DescuentoCantidadReparacionesService {

    @Autowired
    DescuentoCantidadReparacionesRepository descuentosCantidadReparacionRepositorio;

    public List<DescuentoCantidadReparacionesEntity> getDescuentosCantidadReparacion() {
        return (List<DescuentoCantidadReparacionesEntity>) descuentosCantidadReparacionRepositorio.findAll();
    }

    public DescuentoCantidadReparacionesEntity getDescuentosCantidadReparacionById(Long id) {
        return descuentosCantidadReparacionRepositorio.findById(id).get();
    }

    public List<DescuentoCantidadReparacionesEntity> getDescuentosCantidadReparacionByTipoMotor(String tipoMotor) {
        return descuentosCantidadReparacionRepositorio.findByTipoMotor(tipoMotor);
    }

    public List<DescuentoCantidadReparacionesEntity> getDescuentosCantidadReparacionByCantidadReparacionesMin(int cantidadReparaciones) {
        return descuentosCantidadReparacionRepositorio.findByCantidadReparacionesMin(cantidadReparaciones);
    }

    public DescuentoCantidadReparacionesEntity saveDescuentosCantidadReparacion(DescuentoCantidadReparacionesEntity descuentosCantidadReparacion) {
        return descuentosCantidadReparacionRepositorio.save(descuentosCantidadReparacion);
    }

    public DescuentoCantidadReparacionesEntity updateDescuentosCantidadReparacion(DescuentoCantidadReparacionesEntity descuentosCantidadReparacion) {
        return descuentosCantidadReparacionRepositorio.save(descuentosCantidadReparacion);
    }

    public boolean deleteDescuentosCantidadReparacion(Long id) throws Exception {
        try {
            descuentosCantidadReparacionRepositorio.deleteById(id);
            return true;
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    public DescuentoCantidadReparacionesEntity obtenerDescuentoPorTipoMotorYReparaciones(String tipoMotor, int numReparaciones) {
        return descuentosCantidadReparacionRepositorio.searchByCantidadReparacionesMinBetween(tipoMotor, numReparaciones);
    }

    public boolean calcularFechaHaceUnAnio(Date date) {
        Date haceUnAnio = new Date(System.currentTimeMillis() - 365L * 24 * 3600 * 1000);
        return date.after(haceUnAnio);
    }

    public float calcularDescuentoCantidadReparacion(List<Detalle> reparaciones, Vehiculo vehiculo) {
        int numeroReparaciones = (int) reparaciones.stream().filter(r -> r.getPatente().equals(vehiculo.getPatente()) && calcularFechaHaceUnAnio(r.getFechaIngreso())).count();
        System.out.println(numeroReparaciones);
        DescuentoCantidadReparacionesEntity descuento = obtenerDescuentoPorTipoMotorYReparaciones(vehiculo.getTipoMotor(), numeroReparaciones);
        return descuento != null ? descuento.getDescuentoPorNumeroReparaciones() : 0;
    }
}