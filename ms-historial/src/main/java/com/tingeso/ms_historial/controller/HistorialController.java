package com.tingeso.ms_historial.controller;

import com.tingeso.ms_historial.entity.HistorialEntity;
import com.tingeso.ms_historial.model.Detalle;
import com.tingeso.ms_historial.model.Vehiculo;
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

    @GetMapping("/detalles")
    public List<Detalle> getAllDetalles() {
        return historialService.getAllDetalles();
    }

    @GetMapping("/detalles/patente/{patente}")
    public List<Detalle> getAllDetallesByPatente(@PathVariable String patente) {
        return historialService.getAllDetallesByPatente(patente);
    }

    @GetMapping("/vehiculo/patente/{patente}")
    public Vehiculo getVehiculoByPatente(@PathVariable String patente) {
        return historialService.getVehiculoByPatente(patente);
    }

    @GetMapping("/calcular-descuento-dia-atencion/{patente}")
    public float calcularDescuentoDiaAtencion(@PathVariable String patente) {
        return historialService.calcularDescuentoDiaAtencion(patente);
    }

    @GetMapping("/calcular-recargo-retraso/{patente}")
    public float calcularRecargoRetraso(@PathVariable String patente) {
        // Suponiendo que necesitas obtener historiales desde alguna fuente
        List<HistorialEntity> historiales = historialService.getHistorialReparaciones();
        Vehiculo vehiculo = historialService.getVehiculoByPatente(patente);
        return historialService.calcularRecargoRetraso(historiales, vehiculo);
    }

    @GetMapping("/calcular-descuento-cantidad-reparacion/{patente}")
    public float calcularDescuentoCantidadReparacion(@PathVariable String patente) {
        return historialService.calcularDescuentoCantidadReparacion(patente);
    }

    @GetMapping("/calcular-descuento-bono/{patente}")
    public float calcularDescuentoBono(@PathVariable String patente) {
        // Suponiendo que necesitas obtener el vehículo desde alguna fuente
        Vehiculo vehiculo = historialService.getVehiculoByPatente(patente);
        return historialService.calcularDescuentoBono(vehiculo);
    }

    @GetMapping("/calcular-recargo-kilometraje/{patente}")
    public float calcularRecargoKilometraje(@PathVariable String patente) {
        // Suponiendo que necesitas obtener el vehículo desde alguna fuente
        Vehiculo vehiculo = historialService.getVehiculoByPatente(patente);
        return historialService.calcularRecargoKilometraje(vehiculo);
    }

    @GetMapping("/calcular-recargo-antiguedad/{patente}")
    public float calcularRecargoPorAntiguedad(@PathVariable String patente) {
        // Suponiendo que necesitas obtener el vehículo desde alguna fuente
        Vehiculo vehiculo = historialService.getVehiculoByPatente(patente);
        return historialService.calcularRecargoPorAntiguedad(vehiculo);
    }

    @PostMapping("/calcular")
    public ResponseEntity<HistorialEntity> calcularHistorial(@RequestBody HistorialEntity historialReparaciones) {
        HistorialEntity historial = historialService.calculoHistorial(historialReparaciones);
        historial =  historialService.updateHistorialReparaciones(historial);
        return ResponseEntity.ok(historial);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> deleteHistorialReparacionesById(@PathVariable Long id) throws Exception {
        var isDeleted = historialService.deleteHistorialReparaciones(id);
        return ResponseEntity.noContent().build();
    }
}
