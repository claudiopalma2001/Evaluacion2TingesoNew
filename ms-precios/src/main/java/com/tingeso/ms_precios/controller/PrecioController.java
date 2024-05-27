package com.tingeso.ms_precios.controller;

import com.tingeso.ms_precios.entity.PrecioEntity;
import com.tingeso.ms_precios.service.PrecioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/precios")
public class PrecioController {

    @Autowired
    PrecioService preciosReparacionServicio;

    @GetMapping("/")
    public ResponseEntity<List<PrecioEntity>> listarPreciosReparaciones() {
        List<PrecioEntity> preciosReparaciones = preciosReparacionServicio.getPreciosReparacion();
        return ResponseEntity.ok(preciosReparaciones);
    }

    @GetMapping("/id/{id}")
    public ResponseEntity<PrecioEntity> getPreciosReparacionById(@PathVariable Long id) {
        PrecioEntity preciosReparacion = preciosReparacionServicio.getPreciosReparacionById(id);
        return ResponseEntity.ok(preciosReparacion);
    }

    @GetMapping("/tipoMotor/{tipoMotor}")
    public ResponseEntity<List<PrecioEntity>> getPreciosReparacionByTipoMotor(@PathVariable String tipoMotor) {
        List<PrecioEntity> preciosReparaciones = preciosReparacionServicio.getPreciosReparacionByTipoDeMotor(tipoMotor);
        return ResponseEntity.ok(preciosReparaciones);
    }


    @PostMapping("/")
    public ResponseEntity<PrecioEntity> savePreciosReparacion(@RequestBody PrecioEntity preciosReparacion) {
        PrecioEntity preciosReparacionesNuevo = preciosReparacionServicio.savePreciosReparacion(preciosReparacion);
        return ResponseEntity.ok(preciosReparacionesNuevo);
    }

    @PutMapping("/")
    public ResponseEntity<PrecioEntity> updatePreciosReparacion(@RequestBody PrecioEntity preciosReparacion){
        PrecioEntity preciosReparacionesActualizado = preciosReparacionServicio.updatePreciosReparacion(preciosReparacion);
        return ResponseEntity.ok(preciosReparacionesActualizado);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> deletePreciosReparacionById(@PathVariable Long id) throws Exception {
        var isDeleted = preciosReparacionServicio.deletePreciosReparacion(id);
        return ResponseEntity.noContent().build();
    }
}
