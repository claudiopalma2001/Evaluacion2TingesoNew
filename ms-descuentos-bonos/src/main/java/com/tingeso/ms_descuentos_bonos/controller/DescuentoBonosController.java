package com.tingeso.ms_descuentos_bonos.controller;

import com.tingeso.ms_descuentos_bonos.entity.DescuentoBonosEntity;
import com.tingeso.ms_descuentos_bonos.model.Vehiculo;
import com.tingeso.ms_descuentos_bonos.service.DescuentoBonosService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/descuentos_bonos")
public class DescuentoBonosController {

    @Autowired
    DescuentoBonosService descuentosBonosServicio;

    @GetMapping("/")
    public ResponseEntity<List<DescuentoBonosEntity>> listarDescuentosBonos() {
        List<DescuentoBonosEntity> descuentosBonos = descuentosBonosServicio.getDescuentoBonos();
        return ResponseEntity.ok(descuentosBonos);
    }

    @GetMapping("/id/{id}")
    public ResponseEntity<DescuentoBonosEntity> getDescuentoBonosById(@PathVariable Long id) {
        DescuentoBonosEntity descuentosBonos = descuentosBonosServicio.getDescuentoBonosById(id);
        return ResponseEntity.ok(descuentosBonos);
    }

    @GetMapping("/marca/{marca}")
    public ResponseEntity<Optional<DescuentoBonosEntity>> getDescuentoBonosByMarca(@PathVariable String marca) {
        Optional<DescuentoBonosEntity> descuentosBonos = descuentosBonosServicio.getDescuentoBonosByMarca(marca);
        return ResponseEntity.ok(descuentosBonos);
    }

    @PostMapping("/")
    public ResponseEntity<DescuentoBonosEntity> saveDescuentoBonos(@RequestBody DescuentoBonosEntity descuentosBonos) {
        DescuentoBonosEntity descuentosBonosNuevo = descuentosBonosServicio.saveDescuentoBonos(descuentosBonos);
        return ResponseEntity.ok(descuentosBonosNuevo);
    }

    @PostMapping("/update")
    public ResponseEntity<DescuentoBonosEntity> updateDescuentoBonos(@RequestBody DescuentoBonosEntity descuentoBonos) {
        DescuentoBonosEntity updatedBono = descuentosBonosServicio.updateDescuentoBonos(descuentoBonos);
        return ResponseEntity.ok(updatedBono);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> deleteDescuentoBonosById(@PathVariable Long id) throws Exception {
        var isDeleted = descuentosBonosServicio.deleteDescuentoBonos(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/calcular_descuento_bono/{marca}")
    public ResponseEntity<Float> calcularDescuentoBono(@PathVariable String marca) {
        Vehiculo vehiculo = new Vehiculo();
        vehiculo.setMarca(marca);
        float descuento = descuentosBonosServicio.calcularDescuentoBono(vehiculo);
        if (descuento > 0) {
            return ResponseEntity.ok(descuento);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
