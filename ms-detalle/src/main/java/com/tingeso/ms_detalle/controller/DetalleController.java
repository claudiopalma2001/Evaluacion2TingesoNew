package com.tingeso.ms_detalle.controller;

import com.tingeso.ms_detalle.entity.DetalleEntity;
import com.tingeso.ms_detalle.service.DetalleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/detalles")
public class DetalleController {

    @Autowired
    DetalleService detalleService;

    @GetMapping("/")
    public ResponseEntity<List<DetalleEntity>> listarDetalle() {
        List<DetalleEntity> detalle = detalleService.getDetalle();
        return ResponseEntity.ok(detalle);
    }

    @GetMapping("/id/{id}")
    public ResponseEntity<DetalleEntity> getDetalleById(@PathVariable Long id) {
        DetalleEntity detalle = detalleService.getDetalleById(id);
        return ResponseEntity.ok(detalle);
    }

    @GetMapping("/idReparacion/{idReparacion}")
    public ResponseEntity<DetalleEntity> getDetalleByIdReparacion(@PathVariable Long idReparacion) {
        DetalleEntity detalle = detalleService.getDetalleByIdReparacion(idReparacion);
        return ResponseEntity.ok(detalle);
    }

    @GetMapping("/patente/{patente}")
    public ResponseEntity<DetalleEntity> getDetalleByPatente(@PathVariable String patente) {
        DetalleEntity detalle = detalleService.getDetalleByPatente(patente);
        return ResponseEntity.ok(detalle);
    }

    @GetMapping("/patentes/{patente}")
    public ResponseEntity<List<DetalleEntity>> getDetallesByPatente(@PathVariable String patente) {
        List<DetalleEntity> detalle = detalleService.getDetalleByPatentes(patente);
        return ResponseEntity.ok(detalle);
    }

    @PostMapping("/")
    public ResponseEntity<DetalleEntity> saveDetalle(@RequestBody DetalleEntity detalle) {
        DetalleEntity detalleNuevo = detalleService.saveDetalle(detalle);
        return ResponseEntity.ok(detalleNuevo);
    }

    @PutMapping("/")
    public ResponseEntity<DetalleEntity> updateDetalle(@RequestBody DetalleEntity detalle){
        DetalleEntity detalleActualizado = detalleService.updateDetalle(detalle);
        return ResponseEntity.ok(detalleActualizado);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> deleteDetalleById(@PathVariable Long id) throws Exception {
        var isDeleted = detalleService.deleteDetalle(id);
        return ResponseEntity.noContent().build();
    }
}
