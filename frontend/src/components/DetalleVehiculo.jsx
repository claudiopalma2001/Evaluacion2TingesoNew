import { useEffect, useState } from "react";
import { useParams } from "react-router-dom";
import historialesService from "../services/historiales.service";
import Table from "@mui/material/Table";
import TableBody from "@mui/material/TableBody";
import TableCell from "@mui/material/TableCell";
import TableContainer from "@mui/material/TableContainer";
import TableHead from "@mui/material/TableHead";
import TableRow from "@mui/material/TableRow";
import Paper from "@mui/material/Paper";
import DirectionsCarIcon from "@mui/icons-material/DirectionsCar";

const DetallesVehiculo = () => {
  const { patente } = useParams();
  const [detalles, setDetalles] = useState([]);

  useEffect(() => {
    if (patente) {
      historialesService.getDetallesVehiculo(patente)
        .then(response => {
          setDetalles(response.data);
        })
        .catch(error => {
          console.error('Error al obtener los detalles del vehículo', error);
        });
    }
  }, [patente]);

  return (
    <TableContainer component={Paper}>
      <Table sx={{ minWidth: 650 }} size="small" aria-label="a dense table">
        <TableHead>
          <TableRow>
            <TableCell align="left" sx={{ fontWeight: "bold" }}>
              Patente
            </TableCell>
            <TableCell align="left" sx={{ fontWeight: "bold" }}>
              ID de Reparacion
            </TableCell>
            <TableCell align="right" sx={{ fontWeight: "bold" }}>
              Fecha de Ingreso
            </TableCell>
            <TableCell align="right" sx={{ fontWeight: "bold" }}>
              Hora Ingreso
            </TableCell>
            <TableCell align="left" sx={{ fontWeight: "bold" }}>
              Monto Reparacion
            </TableCell>
          </TableRow>
        </TableHead>
        <TableBody>
          {detalles.map((detalle) => (
            <TableRow
              key={detalle.id}
              sx={{ "&:last-child td, &:last-child th": { border: 0 } }}
            >
              <TableCell align="left">{detalle.patente}</TableCell>
              <TableCell align="left">{detalle.idReparacion}</TableCell>
              <TableCell align="right">{detalle.fechaIngreso}</TableCell>
              <TableCell align="right">{detalle.horaIngreso}</TableCell>
              <TableCell align="right">{detalle.montoReparacion}</TableCell>
            </TableRow>
          ))}
        </TableBody>
      </Table>
    </TableContainer>
  );
};

export default DetallesVehiculo;

