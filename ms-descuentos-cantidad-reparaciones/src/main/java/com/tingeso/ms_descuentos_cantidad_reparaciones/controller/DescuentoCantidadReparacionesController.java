package com.tingeso.ms_descuentos_cantidad_reparaciones.controller;

import com.tingeso.ms_descuentos_cantidad_reparaciones.dto.CalculoRequest;
import com.tingeso.ms_descuentos_cantidad_reparaciones.entity.DescuentoCantidadReparacionesEntity;
import com.tingeso.ms_descuentos_cantidad_reparaciones.service.DescuentoCantidadReparacionesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/descuentos_cantidad_reparacion")
public class DescuentoCantidadReparacionesController {

    @Autowired
    DescuentoCantidadReparacionesService descuentosCantidadReparacionServicio;

    @GetMapping("/")
    public ResponseEntity<List<DescuentoCantidadReparacionesEntity>> listarDescuentosCantidadReparacion() {
        List<DescuentoCantidadReparacionesEntity> descuentosCantidadReparacion = descuentosCantidadReparacionServicio.getDescuentosCantidadReparacion();
        return ResponseEntity.ok(descuentosCantidadReparacion);
    }

    @GetMapping("/id/{id}")
    public ResponseEntity<DescuentoCantidadReparacionesEntity> getDescuentoCantidadReparacionById(@PathVariable Long id) {
        DescuentoCantidadReparacionesEntity descuentosCantidadReparacion = descuentosCantidadReparacionServicio.getDescuentosCantidadReparacionById(id);
        return ResponseEntity.ok(descuentosCantidadReparacion);
    }

    @GetMapping("/tipoMotor/{tipoMotor}")
    public ResponseEntity<List<DescuentoCantidadReparacionesEntity>> getDescuentoCantidadReparacionByTipoMotor(@PathVariable String tipoMotor) {
        List<DescuentoCantidadReparacionesEntity> descuentosCantidadReparacion = descuentosCantidadReparacionServicio.getDescuentosCantidadReparacionByTipoMotor(tipoMotor);
        return ResponseEntity.ok(descuentosCantidadReparacion);
    }

    @GetMapping("/cantidadReparaciones/{cantidadReparaciones}")
    public ResponseEntity<List<DescuentoCantidadReparacionesEntity>> getDescuentoCantidadReparacionByCantidadReparaciones(@PathVariable int cantidadReparaciones) {
        List<DescuentoCantidadReparacionesEntity> descuentosCantidadReparacion = descuentosCantidadReparacionServicio.getDescuentosCantidadReparacionByCantidadReparacionesMin(cantidadReparaciones);
        return ResponseEntity.ok(descuentosCantidadReparacion);
    }

    @PostMapping("/")
    public ResponseEntity<DescuentoCantidadReparacionesEntity> saveDescuentoCantidadReparacion(@RequestBody DescuentoCantidadReparacionesEntity descuentosCantidadReparacion) {
        DescuentoCantidadReparacionesEntity descuentoCantidadReparacionNuevo = descuentosCantidadReparacionServicio.saveDescuentosCantidadReparacion(descuentosCantidadReparacion);
        return ResponseEntity.ok(descuentoCantidadReparacionNuevo);
    }

    @PutMapping("/")
    public ResponseEntity<DescuentoCantidadReparacionesEntity> updateDescuentoCantidadReparacion(@RequestBody DescuentoCantidadReparacionesEntity descuentosCantidadReparacion){
        DescuentoCantidadReparacionesEntity descuentoCantidadReparacionActualizado = descuentosCantidadReparacionServicio.updateDescuentosCantidadReparacion(descuentosCantidadReparacion);
        return ResponseEntity.ok(descuentoCantidadReparacionActualizado);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> deleteDescuentoCantidadReparacionById(@PathVariable Long id) throws Exception {
        var isDeleted = descuentosCantidadReparacionServicio.deleteDescuentosCantidadReparacion(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/calcular_descuento_cantidad_reparacion")
    public ResponseEntity<Float> calcularDescuentoCantidadReparacion(@RequestBody CalculoRequest request) {
        float descuento = descuentosCantidadReparacionServicio.calcularDescuentoCantidadReparacion(request.getReparaciones(), request.getVehiculo());
        if (descuento > 0) {
            return ResponseEntity.ok(descuento);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
