package com.tingeso.ms_historial.service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.time.Year;
import com.tingeso.ms_historial.entity.HistorialEntity;
import com.tingeso.ms_historial.model.DescuentoBonos;
import com.tingeso.ms_historial.model.DescuentoCantidadReparaciones;
import com.tingeso.ms_historial.model.Detalle;
import com.tingeso.ms_historial.model.Vehiculo;
import com.tingeso.ms_historial.repository.HistorialRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.sql.Time;
import java.util.Calendar;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;

@SuppressWarnings("ALL")
@Service
public class HistorialService {

    @Autowired
    HistorialRepository historialRepository;

    @Autowired
    ObjectMapper objectMapper;

    @Autowired
    RestTemplate restTemplate;

    public List<HistorialEntity> getHistorialReparaciones() {
        return (List<HistorialEntity>) historialRepository.findAll();
    }

    public HistorialEntity getHistorialReparacionesById(Long id) {
        return historialRepository.findById(id).get();
    }

    public HistorialEntity getHistorialReparacionesByPatente(String patente) {
        return historialRepository.findByPatente(patente);
    }

    public List<HistorialEntity> getHistorialReparacionesByPatentes(String patente) {
        return historialRepository.findAllByPatente(patente);
    }


    public HistorialEntity saveHistorialReparaciones(HistorialEntity historialReparaciones) {
        return historialRepository.save(historialReparaciones);
    }

    public HistorialEntity updateHistorialReparaciones(HistorialEntity historialReparaciones) {
        return historialRepository.save(historialReparaciones);
    }

    public boolean deleteHistorialReparaciones(Long id) throws Exception {
        try {
            historialRepository.deleteById(id);
            return true;
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    public List<Detalle> getAllDetalles() {
        List<Detalle> detalles = restTemplate.getForObject("http://ms-detalle/detalles/", List.class);
        System.out.println(detalles);
        return detalles;
    }

    public List<Detalle> getAllDetallesByPatente(String patente) {
        List<Detalle> detallesPorPatente = restTemplate.getForObject("http://ms-detalle/detalles/patentes/" + patente, List.class);
        System.out.println(detallesPorPatente);
        return detallesPorPatente;
    }

    public Vehiculo getVehiculoByPatente(String patente) {
        Vehiculo vehiculo = restTemplate.getForObject("http://ms-vehiculo/vehiculos/patente/" + patente, Vehiculo.class);
        System.out.println(vehiculo);
        return vehiculo;
    }

    public float calcularDescuentoDiaAtencion(String patente) {
        try {

            List<LinkedHashMap> response = restTemplate.getForObject("http://ms-detalle/detalles/patentes/" + patente, List.class);
            List<Detalle> detalles = objectMapper.convertValue(response, new TypeReference<List<Detalle>>() {});
            Date fechaMasReciente = null;
            Time horaMasReciente = null;

            for (Detalle detalle : detalles) {
                if (!detalle.getPatente().equals(patente)) {
                    continue;
                }

                Date fechaActual = detalle.getFechaIngreso();
                Time horaActual = detalle.getHoraIngreso();

                if (fechaMasReciente == null || fechaActual.after(fechaMasReciente) ||
                        (fechaActual.equals(fechaMasReciente) && horaActual.after(horaMasReciente))) {
                    fechaMasReciente = fechaActual;
                    horaMasReciente = horaActual;
                }
            }

            if (fechaMasReciente == null || horaMasReciente == null) {
                return 0;
            }

            Calendar calendar = Calendar.getInstance();
            Calendar calendar2 = Calendar.getInstance();
            calendar.setTime(fechaMasReciente);
            int diaSemana = calendar.get(Calendar.DAY_OF_WEEK);

            calendar2.setTimeInMillis(horaMasReciente.getTime());
            int horaIngreso = calendar2.get(Calendar.HOUR_OF_DAY);

            // Por algún motivo hay que ponerlo desde el domingo para que tome de lunes a jueves, extraño
            if ((diaSemana == Calendar.SUNDAY || diaSemana == Calendar.MONDAY || diaSemana == Calendar.TUESDAY || diaSemana == Calendar.WEDNESDAY) && horaIngreso >= 9 && horaIngreso < 12) {
                return 0.1f;
            } else {
                return 0;
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Error al calcular el descuento por cantidad de reparaciones", e);
        }
    }

    public float calcularRecargoRetraso(List<HistorialEntity> historiales, Vehiculo vehiculo) {
        HistorialEntity historialMasReciente = null;
        for (HistorialEntity historial : historiales) {
            if (historial.getPatente().equals(vehiculo.getPatente())) {
                if (historialMasReciente == null || historial.getFechaRecogida().after(historialMasReciente.getFechaRecogida())) {
                    historialMasReciente = historial;
                }
            }
        }
        if (historialMasReciente == null) {
            throw new IllegalArgumentException("No se encontró historial de reparaciones para el vehículo proporcionado.");
        }

        float montoTotalReparaciones = 0;
        for (HistorialEntity historial : historiales) {
            if (historial.getPatente().equals(vehiculo.getPatente())) {
                montoTotalReparaciones = montoTotalReparaciones + historial.getMontoTotalReparaciones();
            }
        }
        if (montoTotalReparaciones == 0) {
            return 0;
        }
        float recargo;
        Date fechaSalida = historialMasReciente.getFechaSalida();
        Date fechaRecogida = historialMasReciente.getFechaRecogida();
        if (fechaRecogida == null) {
            return 0;
        }
        long diasRetraso = (fechaRecogida.getTime() - fechaSalida.getTime()) / (1000 * 60 * 60 * 24);
        if (diasRetraso <= 0) {
            return 0;
        }
        recargo = montoTotalReparaciones * 0.05f * diasRetraso;
        return recargo;
    }

    public boolean calcularFechaHaceUnAnio(Date date) {
        Date haceUnAnio = new Date(System.currentTimeMillis() - 365L * 24 * 3600 * 1000);
        return date.after(haceUnAnio);
    }

    public float calcularDescuentoCantidadReparacion(String patente) {
        try {

            List<LinkedHashMap> response = restTemplate.getForObject("http://ms-detalle/detalles/patentes/" + patente, List.class);
            List<Detalle> reparaciones = objectMapper.convertValue(response, new TypeReference<List<Detalle>>() {});
            Vehiculo vehiculo = restTemplate.getForObject("http://ms-vehiculo/vehiculos/patente/" + patente, Vehiculo.class);
            System.out.println("Vehiculo obtenido: " + vehiculo);

            if (vehiculo == null) {
                throw new IllegalArgumentException("No se encontró el vehículo con la patente proporcionada.");
            }

            // Contar reparaciones en el último año
            int numeroReparaciones = (int) reparaciones.stream()
                    .filter(r -> r.getPatente().equals(vehiculo.getPatente()) && calcularFechaHaceUnAnio(r.getFechaIngreso()))
                    .count();

            DescuentoCantidadReparaciones descuento = restTemplate.getForObject(
                    "http://ms-descuentos-cantidad-reparaciones/descuentos_cantidad_reparacion/tipoMotorCantidadReparaciones/" + vehiculo.getTipoMotor() + "/" + numeroReparaciones,
                    DescuentoCantidadReparaciones.class);

            return descuento != null ? descuento.getDescuentoPorNumeroReparaciones() : 0;
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Error al calcular el descuento por cantidad de reparaciones", e);
        }
    }

    public float calcularDescuentoBono(Vehiculo vehiculo) {
        DescuentoBonos bono = restTemplate.getForObject(
                "http://ms-descuentos-bonos/descuentos_bonos/marca/" + vehiculo.getMarca(),
                DescuentoBonos.class);

        if (bono != null && bono.getCantidadDescuentos() > 0) {
            bono.setCantidadDescuentos(bono.getCantidadDescuentos() - 1);
            return bono.getMontoDescuentos();
        }
        return 0;
    }

    public float calcularRecargoKilometraje(Vehiculo vehiculo) {
        float kilometraje = vehiculo.getKilometraje();
        if (kilometraje < 0) {
            return 0;
        }

        String tipoVehiculo = vehiculo.getTipoVehiculo();

        Float recargo = restTemplate.getForObject(
                "http://ms-recargos-kilometraje/recargo_kilometraje/KilometrajeTipoVehiculo/" + kilometraje + "/" + tipoVehiculo,
                Float.class);

        return recargo != null ? recargo : 0;
    }

    public float calcularRecargoPorAntiguedad(Vehiculo vehiculo) {
        int anioVehiculo = vehiculo.getAnio();
        int anioActual = Year.now().getValue();
        int antiguedad = anioActual - anioVehiculo;

        if (antiguedad > 999 || antiguedad < 0) {
            return 0;
        }

        String tipoVehiculo = vehiculo.getTipoVehiculo();

        Float recargo = restTemplate.getForObject(
                "http://ms-recargos-antiguedad/recargo_antiguedad/antiguedadTipoVehiculo/" + antiguedad + "/" + tipoVehiculo,
                Float.class);

        return recargo != null ? recargo : 0;
    }

    public HistorialEntity calculoHistorial(String patente){
        HistorialEntity historial = historialRepository.findByPatente(patente);
        List<HistorialEntity> historiales = getHistorialReparaciones();
        List<LinkedHashMap> response = restTemplate.getForObject("http://ms-detalle/detalles/patentes/" + patente, List.class);
        List<Detalle> detalles = objectMapper.convertValue(response, new TypeReference<List<Detalle>>() {});
        Vehiculo vehiculo = getVehiculoByPatente(patente);

        if (vehiculo == null) {
            throw new IllegalArgumentException("No existe el vehiculo.");
        }
        if (detalles.isEmpty()) {
            throw new IllegalArgumentException("No se encontraron datos para la patente proporcionada.");
        }

        float montoTotalReparaciones = detalles.stream()
                .mapToInt(Detalle::getMontoReparacion)
                .sum();

        float descuentoPorCantidadReparaciones = calcularDescuentoCantidadReparacion(patente) * montoTotalReparaciones;
        float descuentoPorDiaAtencion = calcularDescuentoDiaAtencion(patente) * montoTotalReparaciones;
        float descuentoPorBonos = calcularDescuentoBono(vehiculo);
        float recargoPorKilometraje = calcularRecargoKilometraje(vehiculo) * montoTotalReparaciones;
        float recargoPorAntiguedad = calcularRecargoPorAntiguedad(vehiculo) * montoTotalReparaciones;
        float recargoPorRetraso = calcularRecargoRetraso(historiales, vehiculo);

        float totalDescuentos = descuentoPorCantidadReparaciones + descuentoPorDiaAtencion + descuentoPorBonos;
        float totalRecargos = recargoPorKilometraje + recargoPorAntiguedad + recargoPorRetraso;

        float costoBase = montoTotalReparaciones + totalRecargos - totalDescuentos;
        float iva = costoBase * 0.19f;
        float costoTotal = costoBase + iva;

        historial.setMontoTotalReparaciones(montoTotalReparaciones);
        historial.setDescuentos(totalDescuentos);
        historial.setRecargos(totalRecargos);
        historial.setIva(iva);
        historial.setCostoTotal(costoTotal);
        return historial;
    }
}
