import { useEffect, useState } from "react";
import { Link, useNavigate } from "react-router-dom";
import detallesService from "../services/detalles.service";
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

const DetalleList = () => {
  const [detalles, setDetalles] = useState([]);

  const navigate = useNavigate();

  const init = () => {
    detallesService
      .getAll()
      .then((response) => {
        console.log("Mostrando listado de todos los detalles.", response.data);
        setDetalles(response.data);
      })
      .catch((error) => {
        console.log(
          "Se ha producido un error al intentar mostrar listado de todos los detalles.",
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
      "¿Esta seguro que desea borrar este detalle?"
    );
    if (confirmDelete) {
      detallesService
        .remove(id)
        .then((response) => {
          console.log("Detalle ha sido eliminado.", response.data);
          init();
        })
        .catch((error) => {
          console.log(
            "Se ha producido un error al intentar eliminar el detalle",
            error
          );
        });
    }
  };

  const handleEdit = (id) => {
    console.log("Mostrando id", id);
    navigate(`/detalle/edit/${id}`);
  };

  return (
    <TableContainer component={Paper}>
      <br />
      <Link
        to="/detalle/add"
        style={{ textDecoration: "none", marginBottom: "1rem" }}
      >
        <Button
          variant="contained"
          color="primary"
          startIcon={<DirectionsCarIcon />}
        >
          Añadir Detalle
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
              <TableCell>
                <Button
                  variant="contained"
                  color="info"
                  size="small"
                  onClick={() => handleEdit(detalle.id)}
                  style={{ marginLeft: "0.5rem" }}
                  startIcon={<EditIcon />}
                >
                  Editar
                </Button>

                <Button
                  variant="contained"
                  color="error"
                  size="small"
                  onClick={() => handleDelete(detalle.id)}
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

export default DetalleList;