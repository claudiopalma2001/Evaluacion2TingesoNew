import { useEffect, useState } from "react";
import { Link, useNavigate } from "react-router-dom";
import Table from "@mui/material/Table";
import TableBody from "@mui/material/TableBody";
import TableCell, { tableCellClasses } from "@mui/material/TableCell";
import TableContainer from "@mui/material/TableContainer";
import TableHead from "@mui/material/TableHead";
import TableRow from "@mui/material/TableRow";
import Paper from "@mui/material/Paper";
import preciosService from "../services/precios.service";

const PrecioList = () => {
  const [precios, setPrecios] = useState([]);

  const navigate = useNavigate();

  const init = () => {
    preciosService
      .getAll()
      .then((response) => {
        console.log("Mostrando listado de todos los precios.", response.data);
        setPrecios(response.data);
      })
      .catch((error) => {
        console.log(
          "Se ha producido un error al intentar mostrar listado de todos los precios.",
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
      "¿Esta seguro que desea borrar este precio?"
    );
    if (confirmDelete) {
      preciosService
        .remove(id)
        .then((response) => {
          console.log("precio ha sido eliminado.", response.data);
          init();
        })
        .catch((error) => {
          console.log(
            "Se ha producido un error al intentar eliminar el precio",
            error
          );
        });
    }
  };

  const handleEdit = (id) => {
    console.log("Mostrando id", id);
    navigate(`/precio/edit/${id}`);
  };

  return (
    <TableContainer component={Paper}>
      <br />
      <Table sx={{ minWidth: 650 }} size="small" aria-label="a dense table">
        <TableHead>
          <TableRow>
            <TableCell align="left" sx={{ fontWeight: "bold" }}>
              ID Reparacion
            </TableCell>
            <TableCell align="left" sx={{ fontWeight: "bold" }}>
              Tipo de motor
            </TableCell>
            <TableCell align="right" sx={{ fontWeight: "bold" }}>
              Precio
            </TableCell>
          </TableRow>
        </TableHead>
        <TableBody>
          {precios.map((precio) => (
            <TableRow
              key={precio.id}
              sx={{ "&:last-child td, &:last-child th": { border: 0 } }}
            >
              <TableCell align="left">{precio.idReparacion}</TableCell>
              <TableCell align="middle">{precio.tipoDeMotor}</TableCell>
              <TableCell align="right">{precio.precio}</TableCell>
            </TableRow>
          ))}
        </TableBody>
      </Table>
    </TableContainer>
  );
};

export default PrecioList;