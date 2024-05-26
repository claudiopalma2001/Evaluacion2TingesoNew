package com.tingeso.ms_vehiculo.controller;

import com.tingeso.ms_vehiculo.entity.VehiculoEntity;
import com.tingeso.ms_vehiculo.service.VehiculoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/vehiculos")
public class VehiculoController {

    @Autowired
    VehiculoService vehiculosServicio;

    @GetMapping("/")
    public ResponseEntity<List<VehiculoEntity>> listarVehiculos() {
        List<VehiculoEntity> vehiculos = vehiculosServicio.getVehiculos();
        return ResponseEntity.ok(vehiculos);
    }

    @GetMapping("/id/{id}")
    public ResponseEntity<VehiculoEntity> getVehiculoById(@PathVariable Long id) {
        VehiculoEntity vehiculo = vehiculosServicio.getVehiculoById(id);
        return ResponseEntity.ok(vehiculo);
    }

    @GetMapping("/patente/{patente}")
    public ResponseEntity<VehiculoEntity> getVehiculoByPatente(@PathVariable String patente) {
        VehiculoEntity vehiculo = vehiculosServicio.getVehiculoByPatente(patente);
        return ResponseEntity.ok(vehiculo);
    }

    @GetMapping("/tipoVehiculo/{tipoVehiculo}")
    public ResponseEntity<List<VehiculoEntity>> getVehiculosByTipoVehiculo(@PathVariable String tipoVehiculo) {
        List<VehiculoEntity> vehiculos = vehiculosServicio.getVehiculosByTipoVehiculo(tipoVehiculo);
        return ResponseEntity.ok(vehiculos);
    }

    @GetMapping("/tipoMotor/{tipoMotor}")
    public ResponseEntity<List<VehiculoEntity>> getVehiculosByTipoMotor(@PathVariable String tipoMotor) {
        List<VehiculoEntity> vehiculos = vehiculosServicio.getVehiculosByTipoMotor(tipoMotor);
        return ResponseEntity.ok(vehiculos);
    }

    @GetMapping("/marca/{marca}")
    public ResponseEntity<List<VehiculoEntity>> getVehiculosByMarca(@PathVariable String marca) {
        List<VehiculoEntity> vehiculos = vehiculosServicio.getVehiculosByMarca(marca);
        return ResponseEntity.ok(vehiculos);
    }

    @PostMapping("/")
    public ResponseEntity<VehiculoEntity> saveVehiculo(@RequestBody VehiculoEntity vehiculo) {
        VehiculoEntity vehiculoNuevo = vehiculosServicio.saveVehiculo(vehiculo);
        return ResponseEntity.ok(vehiculoNuevo);
    }

    @PutMapping("/")
    public ResponseEntity<VehiculoEntity> updateVehiculo(@RequestBody VehiculoEntity vehiculo){
        VehiculoEntity vehiculoActualizado = vehiculosServicio.updateVehiculo(vehiculo);
        return ResponseEntity.ok(vehiculoActualizado);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> deleteVehiculoById(@PathVariable Long id) throws Exception {
        var isDeleted = vehiculosServicio.deleteVehiculo(id);
        return ResponseEntity.noContent().build();
    }
}

