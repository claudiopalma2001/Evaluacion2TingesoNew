package com.tingeso.ms_historial.controller;

import com.tingeso.ms_historial.entity.HistorialEntity;
import com.tingeso.ms_historial.service.HistorialService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/historial")
public class HistorialController {

    @Autowired
    HistorialService historialService;

    @GetMapping("/")
    public ResponseEntity<List<HistorialEntity>> listarHistorialReparaciones() {
        List<HistorialEntity> historialReparaciones = historialService.getHistorialReparaciones();
        return ResponseEntity.ok(historialReparaciones);
    }

    @GetMapping("/id/{id}")
    public ResponseEntity<HistorialEntity> getHistorialReparacionesById(@PathVariable Long id) {
        HistorialEntity historialReparaciones = historialService.getHistorialReparacionesById(id);
        return ResponseEntity.ok(historialReparaciones);
    }

    @GetMapping("/patente/{patente}")
    public ResponseEntity<HistorialEntity> getHistorialReparacionesByPatente(@PathVariable String patente) {
        HistorialEntity historialReparaciones = historialService.getHistorialReparacionesByPatente(patente);
        return ResponseEntity.ok(historialReparaciones);
    }

    @GetMapping("/patentes/{patente}")
    public ResponseEntity<List<HistorialEntity>> getHistorialesReparacionesByPatente(@PathVariable String patente) {
        List<HistorialEntity> historialReparaciones = historialService.getHistorialReparacionesByPatentes(patente);
        return ResponseEntity.ok(historialReparaciones);
    }

    @PostMapping("/")
    public ResponseEntity<HistorialEntity> saveHistorialReparaciones(@RequestBody HistorialEntity historialReparaciones) {
        HistorialEntity historialNuevo = historialService.saveHistorialReparaciones(historialReparaciones);
        return ResponseEntity.ok(historialNuevo);
    }

    @PutMapping("/")
    public ResponseEntity<HistorialEntity> updateHistorialReparaciones(@RequestBody HistorialEntity historialReparaciones){
        HistorialEntity historialActualizado = historialService.updateHistorialReparaciones(historialReparaciones);
        return ResponseEntity.ok(historialActualizado);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> deleteHistorialReparacionesById(@PathVariable Long id) throws Exception {
        var isDeleted = historialService.deleteHistorialReparaciones(id);
        return ResponseEntity.noContent().build();
    }
}
