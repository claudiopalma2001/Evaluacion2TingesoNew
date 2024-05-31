import { useEffect, useState } from "react";
import { Link, useNavigate } from "react-router-dom";
import historialesService from "../services/historiales.service";
import Table from "@mui/material/Table";
import TableBody from "@mui/material/TableBody";
import TableCell from "@mui/material/TableCell";
import TableContainer from "@mui/material/TableContainer";
import TableHead from "@mui/material/TableHead";
import TableRow from "@mui/material/TableRow";
import Paper from "@mui/material/Paper";
import Button from "@mui/material/Button";
import CarRepairIcon from "@mui/icons-material/CarRepair";
import EditIcon from "@mui/icons-material/Edit";
import DeleteIcon from "@mui/icons-material/Delete";
import CalculateIcon from "@mui/icons-material/Calculate";
import InfoIcon from "@mui/icons-material/Info";  // Import the icon for "Ver detalle"

const HistorialList = () => {
  const [historiales, setHistoriales] = useState([]);
  const navigate = useNavigate();

  const init = () => {
    historialesService
      .getAll()
      .then((response) => {
        console.log("Mostrando listado de todo el historial.", response.data);
        setHistoriales(response.data);
      })
      .catch((error) => {
        console.log(
          "Se ha producido un error al intentar mostrar listado de todos las reparaciones.",
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
      "¿Esta seguro que desea borrar esta reparacion?"
    );
    if (confirmDelete) {
      historialesService
        .remove(id)
        .then((response) => {
          console.log("Reparacion ha sido eliminada.", response.data);
          init();
        })
        .catch((error) => {
          console.log(
            "Se ha producido un error al intentar eliminar la reparacion",
            error
          );
        });
    }
  };

  const handleEdit = (id) => {
    console.log("Mostrando id", id);
    navigate(`/historial/edit/${id}`);
  };

  const handleCalculate = async (patente) => {
    try {
      const response = await historialesService.calculate(patente);
      console.log("Datos calculados", response.data);
      init();
    } catch (error) {
      console.log("Error al calcular los datos", error);
    }
  };

  const handleViewDetails = (patente) => {
    navigate(`/historial/detalles/patente/${patente}`);
  };

  const handleViewDescuentos = (patente) => {
    navigate(`/historial/descuentos/${patente}`); 
  };

  const handleViewRecargos = (patente) => {
    navigate(`/historial/recargos/${patente}`); 
  };

  return (
    <TableContainer component={Paper}>
      <br />
      <Link
        to="/historial/add"
        style={{ textDecoration: "none", marginBottom: "1rem" }}
      >
        <Button
          variant="contained"
          color="primary"
          startIcon={<CarRepairIcon />}
        >
          Añadir Reparacion
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
              Fecha Ingreso
            </TableCell>
            <TableCell align="right" sx={{ fontWeight: "bold" }}>
              Hora Ingreso
            </TableCell>
            <TableCell align="right" sx={{ fontWeight: "bold" }}>
              Monto Total Reparación
            </TableCell>
            <TableCell align="right" sx={{ fontWeight: "bold" }}>
              Recargos
            </TableCell>
            <TableCell align="right" sx={{ fontWeight: "bold" }}>
              Descuentos
            </TableCell>
            <TableCell align="right" sx={{ fontWeight: "bold" }}>
              IVA
            </TableCell>
            <TableCell align="right" sx={{ fontWeight: "bold" }}>
              Costo Total
            </TableCell>
            <TableCell align="left" sx={{ fontWeight: "bold" }}>
              Fecha Salida
            </TableCell>
            <TableCell align="left" sx={{ fontWeight: "bold" }}>
              Hora Salida
            </TableCell>
            <TableCell align="left" sx={{ fontWeight: "bold" }}>
              Fecha Recogida
            </TableCell>
            <TableCell align="left" sx={{ fontWeight: "bold" }}>
              Hora Recogida
            </TableCell>
            <TableCell align="center" sx={{ fontWeight: "bold" }}>
              Acciones
            </TableCell>
          </TableRow>
        </TableHead>
        <TableBody>
          {historiales.map((historial) => (
            <TableRow
              key={historial.id}
              sx={{ "&:last-child td, &:last-child th": { border: 0 } }}
            >
              <TableCell align="left">{historial.patente}</TableCell>
              <TableCell align="left">{historial.fechaIngreso}</TableCell>
              <TableCell align="right">{historial.horaIngreso}</TableCell>
              <TableCell align="right">{historial.montoTotalReparaciones}</TableCell>
              <TableCell align="right">{historial.recargos}</TableCell>
              <TableCell align="right">{historial.descuentos}</TableCell>
              <TableCell align="right">{historial.iva}</TableCell>
              <TableCell align="right">{historial.costoTotal}</TableCell>
              <TableCell align="right">{historial.fechaSalida}</TableCell>
              <TableCell align="right">{historial.horaSalida}</TableCell>
              <TableCell align="right">{historial.fechaRecogida}</TableCell>
              <TableCell align="right">{historial.horaRecogida}</TableCell>
              <TableCell align="center">
                <Button
                  variant="contained"
                  color="info"
                  size="small"
                  onClick={() => handleEdit(historial.id)}
                  style={{ marginLeft: "0.5rem" }}
                  startIcon={<EditIcon />}
                >
                  Editar
                </Button>
                <Button
                  variant="contained"
                  color="error"
                  size="small"
                  onClick={() => handleDelete(historial.id)}
                  style={{ marginLeft: "0.5rem" }}
                  startIcon={<DeleteIcon />}
                >
                  Eliminar
                </Button>
                {historial.montoTotalReparaciones === 0 && (
                  <Button
                    variant="contained"
                    color="primary"
                    size="small"
                    onClick={() => handleCalculate(historial.patente)}
                    style={{ marginLeft: "0.5rem" }}
                    startIcon={<CalculateIcon />}
                  >
                    Calcular
                  </Button>
                )}
                <Button
                  variant="contained"
                  color="secondary"
                  size="small"
                  onClick={() => handleViewDetails(historial.patente)}
                  style={{ marginLeft: "0.5rem" }}
                  startIcon={<InfoIcon />}
                >
                  Ver detalle
                </Button>
                <Button
                  variant="contained"
                  color="primary"
                  size="small"
                  onClick={() => handleViewDescuentos(historial.patente)}
                  style={{ marginLeft: "0.5rem" }}
                >
                  Ver cálculo descuentos
                </Button>
                <Button
                  variant="contained"
                  color="primary"
                  size="small"
                  onClick={() => handleViewRecargos(historial.patente)}
                  style={{ marginLeft: "0.5rem" }}
                >
                  Ver cálculo recargos
                </Button>
              </TableCell>
            </TableRow>
          ))}
        </TableBody>
      </Table>
    </TableContainer>
  );
};

export default HistorialList;
