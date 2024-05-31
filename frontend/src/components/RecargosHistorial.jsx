import React, { useState, useEffect } from "react";
import { useParams } from "react-router-dom";
import historialesService from "../services/historiales.service";

const RecargosHistorial = () => {
  const [recargoRetraso, setRecargoRetraso] = useState(0);
  const [recargoAntiguedad, setRecargoAntiguedad] = useState(0);
  const [recargoKilometraje, setRecargoKilometraje] = useState(0);
  const { patente } = useParams();

  useEffect(() => {
    const fetchDescuentos = async () => {
      try {
        const recargoRetrasoResponse = await historialesService.recargoRetraso(patente);
        setRecargoRetraso(recargoRetrasoResponse.data);

        const recargoAntiguedadResponse = await historialesService.recargoAntiguedad(
          patente
        );
        setRecargoAntiguedad(recargoAntiguedadResponse.data);

        const recargoKilometrajeResponse = await historialesService.recargoKilometraje(patente);
        setRecargoKilometraje(recargoKilometrajeResponse.data);
      } catch (error) {
        console.error("Error al obtener los descuentos:", error);
      }
    };

    fetchDescuentos();
  }, [patente]);

  return (
    <div>
      <h2>Recargos del historial con patente: {patente}</h2>
      <ul>
        <li>Recargo por retraso: {recargoRetraso}</li>
        <li>Recargo por antiguedad: {recargoAntiguedad}</li>
        <li>Recargo por kilometraje: {recargoKilometraje}</li>
      </ul>
    </div>
  );
};

export default RecargosHistorial;
