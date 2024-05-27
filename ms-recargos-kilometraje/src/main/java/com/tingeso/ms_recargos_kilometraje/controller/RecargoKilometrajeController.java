package com.tingeso.ms_recargos_kilometraje.controller;

import com.tingeso.ms_recargos_kilometraje.entity.RecargoKilometrajeEntity;
import com.tingeso.ms_recargos_kilometraje.model.Vehiculo;
import com.tingeso.ms_recargos_kilometraje.service.RecargoKilometrajeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/recargo_kilometraje")
public class RecargoKilometrajeController {

    @Autowired
    RecargoKilometrajeService recargoKilometrajeServicio;

    @GetMapping("/")
    public ResponseEntity<List<RecargoKilometrajeEntity>> listarRecargosKilometraje() {
        List<RecargoKilometrajeEntity> recargosKilometraje = recargoKilometrajeServicio.getRecargosKilometraje();
        return ResponseEntity.ok(recargosKilometraje);
    }

    @GetMapping("/id/{id}")
    public ResponseEntity<RecargoKilometrajeEntity> getVehiculoById(@PathVariable Long id) {
        RecargoKilometrajeEntity recargoKilometraje = recargoKilometrajeServicio.getRecargoKilometrajeById(id);
        return ResponseEntity.ok(recargoKilometraje);
    }


    @GetMapping("/tipoVehiculo/{tipoVehiculo}")
    public ResponseEntity<List<RecargoKilometrajeEntity>> getRecargosKilometrajeByTipoVehiculo(@PathVariable String tipoVehiculo) {
        List<RecargoKilometrajeEntity> recargosKilometraje = recargoKilometrajeServicio.getRecargosKilometrajeByTipoVehiculo(tipoVehiculo);
        return ResponseEntity.ok(recargosKilometraje);
    }

    @PostMapping("/")
    public ResponseEntity<RecargoKilometrajeEntity> saveRecargoKilometraje(@RequestBody RecargoKilometrajeEntity recargoKilometraje) {
        RecargoKilometrajeEntity recargoKilometrajeNuevo = recargoKilometrajeServicio.saveRecargoKilometraje(recargoKilometraje);
        return ResponseEntity.ok(recargoKilometrajeNuevo);
    }

    @PutMapping("/")
    public ResponseEntity<RecargoKilometrajeEntity> updateRecargoKilometraje(@RequestBody RecargoKilometrajeEntity recargoKilometraje){
        RecargoKilometrajeEntity recargoKilometrajeActualizado = recargoKilometrajeServicio.updateRecargoKilometraje(recargoKilometraje);
        return ResponseEntity.ok(recargoKilometrajeActualizado);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> deleteRecargoKilometrajeById(@PathVariable Long id) throws Exception {
        var isDeleted = recargoKilometrajeServicio.deleteRecargoKilometraje(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/calcular_recargo_kilometraje")
    public ResponseEntity<Float> calcularRecargoKilometraje(@RequestBody Vehiculo vehiculo) {
        float recargo = recargoKilometrajeServicio.calcularRecargoKilometraje(vehiculo);
        return ResponseEntity.ok(recargo);
    }
}
