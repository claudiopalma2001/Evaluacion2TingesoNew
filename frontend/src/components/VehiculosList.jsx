import { useEffect, useState } from "react";
import { Link, useNavigate } from "react-router-dom";
import vehiculosService from "../services/vehiculos.service";
import Table from "@mui/material/Table";
import TableBody from "@mui/material/TableBody";
import TableCell, { tableCellClasses } from "@mui/material/TableCell";
import TableContainer from "@mui/material/TableContainer";
import TableHead from "@mui/material/TableHead";
import TableRow from "@mui/material/TableRow";
import Paper from "@mui/material/Paper";
import Button from "@mui/material/Button";
import DirectionsCarIcon from "@mui/icons-material/DirectionsCar";
import EditIcon from "@mui/icons-material/Edit";
import DeleteIcon from "@mui/icons-material/Delete";

const VehiculoList = () => {
  const [vehiculos, setVehiculos] = useState([]);

  const navigate = useNavigate();

  const init = () => {
    vehiculosService
      .getAll()
      .then((response) => {
        console.log("Mostrando listado de todos los vehiculos.", response.data);
        setVehiculos(response.data);
      })
      .catch((error) => {
        console.log(
          "Se ha producido un error al intentar mostrar listado de todos los vehiculos.",
          error
        );
      });
  };

  useEffect(() => {
    init();
  }, []);

  const handleDelete = (id) => {
    console.log("Mostrando id", id);
    const confirmDelete = window.confirm(
      "¿Esta seguro que desea borrar este vehiculo?"
    );
    if (confirmDelete) {
      vehiculosService
        .remove(id)
        .then((response) => {
          console.log("Vehiculo ha sido eliminado.", response.data);
          init();
        })
        .catch((error) => {
          console.log(
            "Se ha producido un error al intentar eliminar al vehiculo",
            error
          );
        });
    }
  };

  const handleEdit = (id) => {
    console.log("Mostrando id", id);
    navigate(`/vehiculo/edit/${id}`);
  };

  return (
    <TableContainer component={Paper}>
      <br />
      <Link
        to="/vehiculo/add"
        style={{ textDecoration: "none", marginBottom: "1rem" }}
      >
        <Button
          variant="contained"
          color="primary"
          startIcon={<DirectionsCarIcon />}
        >
          Añadir Vehiculo
        </Button>
      </Link>
      <br /> <br />
      <Table sx={{ minWidth: 650 }} size="small" aria-label="a dense table">
        <TableHead>
          <TableRow>
            <TableCell align="left" sx={{ fontWeight: "bold" }}>
              Patente
            </TableCell>
            <TableCell align="left" sx={{ fontWeight: "bold" }}>
              Marca
            </TableCell>
            <TableCell align="right" sx={{ fontWeight: "bold" }}>
              Modelo
            </TableCell>
            <TableCell align="right" sx={{ fontWeight: "bold" }}>
              TipoVehiculo
            </TableCell>
            <TableCell align="right" sx={{ fontWeight: "bold" }}>
              Año
            </TableCell>
            <TableCell align="left" sx={{ fontWeight: "bold" }}>
              TipoMotor
            </TableCell>
            <TableCell align="left" sx={{ fontWeight: "bold" }}>
              Kilometraje
            </TableCell>
          </TableRow>
        </TableHead>
        <TableBody>
          {vehiculos.map((vehiculo) => (
            <TableRow
              key={vehiculo.id}
              sx={{ "&:last-child td, &:last-child th": { border: 0 } }}
            >
              <TableCell align="left">{vehiculo.patente}</TableCell>
              <TableCell align="left">{vehiculo.marca}</TableCell>
              <TableCell align="right">{vehiculo.modelo}</TableCell>
              <TableCell align="right">{vehiculo.tipoVehiculo}</TableCell>
              <TableCell align="right">{vehiculo.anio}</TableCell>
              <TableCell align="right">{vehiculo.tipoMotor}</TableCell>
              <TableCell align="right">{vehiculo.kilometraje}</TableCell>
              <TableCell>
                <Button
                  variant="contained"
                  color="info"
                  size="small"
                  onClick={() => handleEdit(vehiculo.id)}
                  style={{ marginLeft: "0.5rem" }}
                  startIcon={<EditIcon />}
                >
                  Editar
                </Button>

                <Button
                  variant="contained"
                  color="error"
                  size="small"
                  onClick={() => handleDelete(vehiculo.id)}
                  style={{ marginLeft: "0.5rem" }}
                  startIcon={<DeleteIcon />}
                >
                  Eliminar
                </Button>
              </TableCell>
            </TableRow>
          ))}
        </TableBody>
      </Table>
    </TableContainer>
  );
};

export default VehiculoList;