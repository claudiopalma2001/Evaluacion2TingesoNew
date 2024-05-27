package com.tingeso.ms_descuentos_bonos.service;

import org.springframework.beans.factory.annotation.Autowired;

import com.tingeso.ms_descuentos_bonos.entity.DescuentoBonosEntity;
import com.tingeso.ms_descuentos_bonos.model.Vehiculo;
import com.tingeso.ms_descuentos_bonos.repository.DescuentoBonosRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DescuentoBonosService {

    @Autowired
    DescuentoBonosRepository descuentoBonosRepository;

    public List<DescuentoBonosEntity> getDescuentoBonos() {
        return (List<DescuentoBonosEntity>) descuentoBonosRepository.findAll();
    }

    public DescuentoBonosEntity getDescuentoBonosById(Long id) {
        return descuentoBonosRepository.findById(id).get();
    }

    public Optional<DescuentoBonosEntity> getDescuentoBonosByMarca(String marca) {
        return descuentoBonosRepository.findByMarcaVehiculo(marca);
    }

    public DescuentoBonosEntity saveDescuentoBonos(DescuentoBonosEntity descuentoBonos) {
        return descuentoBonosRepository.save(descuentoBonos);
    }

    public DescuentoBonosEntity updateDescuentoBonos(DescuentoBonosEntity descuentoBonos) {
        return descuentoBonosRepository.save(descuentoBonos);
    }

    public boolean deleteDescuentoBonos(Long id) throws Exception {
        try {
            descuentoBonosRepository.deleteById(id);
            return true;
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    public float calcularDescuentoBono(Vehiculo vehiculo) {
        Optional<DescuentoBonosEntity> bonoExistente = descuentoBonosRepository.findByMarcaVehiculo(vehiculo.getMarca());
        if (bonoExistente.isPresent()) {
            DescuentoBonosEntity bono = bonoExistente.get();
            if (bono.getCantidadDescuentos() > 0) {
                bono.setCantidadDescuentos(bono.getCantidadDescuentos() - 1);
                descuentoBonosRepository.save(bono);
                return bono.getMontoDescuentos();
            }
        }
        return 0;
    }
}
