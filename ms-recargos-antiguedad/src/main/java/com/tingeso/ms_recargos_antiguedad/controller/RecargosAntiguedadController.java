package com.tingeso.ms_recargos_antiguedad.controller;

import com.tingeso.ms_recargos_antiguedad.entity.RecargosAntiguedadEntity;
import com.tingeso.ms_recargos_antiguedad.service.RecargosAntiguedadService;
import com.tingeso.ms_recargos_antiguedad.model.Vehiculo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/recargo_antiguedad")
public class RecargosAntiguedadController {

    @Autowired
    RecargosAntiguedadService recargoAntiguedadServicio;

    @GetMapping("/")
    public ResponseEntity<List<RecargosAntiguedadEntity>> listarRecargosAntiguedad() {
        List<RecargosAntiguedadEntity> recargosAntiguedad = recargoAntiguedadServicio.getRecargosAntiguedad();
        return ResponseEntity.ok(recargosAntiguedad);
    }

    @GetMapping("/id/{id}")
    public ResponseEntity<RecargosAntiguedadEntity> getRecargoAntiguedadById(@PathVariable Long id) {
        RecargosAntiguedadEntity recargoAntiguedad = recargoAntiguedadServicio.getRecargoAntiguedadById(id);
        return ResponseEntity.ok(recargoAntiguedad);
    }

    @GetMapping("/antiguedad/{antiguedadMin}")
    public ResponseEntity<RecargosAntiguedadEntity> getRecargoAntiguedadByAntiguedadMin(@PathVariable int antiguedadMin) {
        RecargosAntiguedadEntity recargoAntiguedad = recargoAntiguedadServicio.getRecargoAntiguedadByAntiguedadMin(antiguedadMin);
        return ResponseEntity.ok(recargoAntiguedad);
    }

    @GetMapping("/tipoVehiculo/{tipoVehiculo}")
    public ResponseEntity<List<RecargosAntiguedadEntity>> getRecargoAntiguedadByTipoVehiculo(@PathVariable String tipoVehiculo) {
        List<RecargosAntiguedadEntity> recargoAntiguedad = recargoAntiguedadServicio.getRecargosAntiguedadByTipoVehiculo(tipoVehiculo);
        return ResponseEntity.ok(recargoAntiguedad);
    }

    @PostMapping("/")
    public ResponseEntity<RecargosAntiguedadEntity> saveRecargoAntiguedad(@RequestBody RecargosAntiguedadEntity recargoAntiguedad) {
        RecargosAntiguedadEntity recargoAntiguedadNuevo = recargoAntiguedadServicio.saveRecargoAntiguedad(recargoAntiguedad);
        return ResponseEntity.ok(recargoAntiguedadNuevo);
    }

    @PutMapping("/")
    public ResponseEntity<RecargosAntiguedadEntity> updateRecargoAntiguedad(@RequestBody RecargosAntiguedadEntity recargoAntiguedad){
        RecargosAntiguedadEntity recargoAntiguedadActualizado = recargoAntiguedadServicio.updateRecargoAntiguedad(recargoAntiguedad);
        return ResponseEntity.ok(recargoAntiguedadActualizado);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> deleteRecargoAntiguedadById(@PathVariable Long id) throws Exception {
        var isDeleted = recargoAntiguedadServicio.deleteRecargoAntiguedad(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/calcular_recargo_antiguedad")
    public ResponseEntity<Float> calcularRecargoAntiguedad(@RequestBody Vehiculo vehiculo) {
        float recargo = recargoAntiguedadServicio.calcularRecargoPorAntiguedad(vehiculo);
        return ResponseEntity.ok(recargo);
    }
}
