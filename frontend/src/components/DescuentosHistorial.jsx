import React, { useState, useEffect } from "react";
import { useParams } from "react-router-dom";
import historialesService from "../services/historiales.service";

const DescuentosHistorial = () => {
  const [descuentoDia, setDescuentoDia] = useState(0);
  const [descuentoCantidad, setDescuentoCantidad] = useState(0);
  const [descuentoPorBono, setDescuentoPorBono] = useState(0);
  const { patente } = useParams();

  useEffect(() => {
    const fetchDescuentos = async () => {
      try {
        const descuentoDiaResponse = await historialesService.descuentoDiaAtencion(patente);
        setDescuentoDia(descuentoDiaResponse.data);

        const descuentoCantidadResponse = await historialesService.descuentoCantidadReparacion(
          patente
        );
        setDescuentoCantidad(descuentoCantidadResponse.data);

        const descuentoBonoResponse = await historialesService.descuentoBono(patente);
        setDescuentoPorBono(descuentoBonoResponse.data);
      } catch (error) {
        console.error("Error al obtener los descuentos:", error);
      }
    };

    fetchDescuentos();
  }, [patente]);

  return (
    <div>
      <h2>Descuentos del historial con patente: {patente}</h2>
      <ul>
        <li>Descuento por día de atención: {descuentoDia}</li>
        <li>Descuento por cantidad de reparaciones: {descuentoCantidad}</li>
        <li>Descuento por bono: {descuentoPorBono}</li>
      </ul>
    </div>
  );
};

export default DescuentosHistorial;
