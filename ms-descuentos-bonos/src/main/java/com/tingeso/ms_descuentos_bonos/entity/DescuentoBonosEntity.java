package com.tingeso.ms_descuentos_bonos.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "descuentosBonos")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class DescuentoBonosEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true, nullable = false)
    private Long id;

    private String marcaVehiculo;
    private int cantidadDescuentos;
    private int montoDescuentos;
}
