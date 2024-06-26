import { useState, useEffect } from "react";
import { Link, useParams, useNavigate } from "react-router-dom";
import Box from "@mui/material/Box";
import TextField from "@mui/material/TextField";
import Button from "@mui/material/Button";
import FormControl from "@mui/material/FormControl";
import MenuItem from "@mui/material/MenuItem";
import SaveIcon from "@mui/icons-material/Save";
import detallesService from "../services/detalles.service";
import preciosService from "../services/precios.service";
import vehiculosService from "../services/vehiculos.service";

const AddEditDetalle = () => {
  const [patente, setPatente] = useState("");
  const [idReparacion, setIdReparacion] = useState("");
  const [fechaIngreso, setFechaIngreso] = useState("");
  const [horaIngreso, setHoraIngreso] = useState("");
  const [montoReparacion, setMontoReparacion] = useState("");
  const [errorPatente, setErrorPatente] = useState(false);
  const { id } = useParams();
  const [titleDetalleForm, setTitleDetalleForm] = useState("");
  const navigate = useNavigate();

  useEffect(() => {
    if (id) {
      setTitleDetalleForm("Editar Detalle");
      detallesService
        .get(id)
        .then((detalle) => {
          setPatente(detalle.data.patente);
          setIdReparacion(detalle.data.idReparacion);
          setFechaIngreso(detalle.data.fechaIngreso);
          setHoraIngreso(detalle.data.horaIngreso);
          setMontoReparacion(detalle.data.montoReparacion);
        })
        .catch((error) => {
          console.log("Se ha producido un error al obtener el detalle.", error);
        });
    } else {
      setTitleDetalleForm("Nuevo Detalle");
    }
  }, [id]);

  const validatePatente = (value) => {
    const patenteRegex = /^[A-Z]{4}\d{2}$/;
    return patenteRegex.test(value);
  };

  const handlePatenteChange = (e) => {
    const newValue = e.target.value.toUpperCase();
    if (newValue.length <= 6) {
      setPatente(newValue);
      setErrorPatente(!validatePatente(newValue));
    }
  };

  const getPrecioReparacion = (idReparacion, patente) => {
    console.log("La patente es:", patente);
    return vehiculosService.getByTipoMotor(patente)
      .then(response => {
        console.log("Respuesta de vehiculosService:", response.data);
        const tipoMotor = response.data;
        console.log("El tipo de motor es:", tipoMotor);
        return preciosService.getPrecio(idReparacion, tipoMotor);
      })
      .then(response => response.data)
      .catch(error => {
        console.log("Error al obtener el precio de la reparación.", error);
        alert("La patente ingresada no se encuentra registrada");
        throw error;
      });
  };
  

  const handleIdReparacionChange = (e) => {
    const newIdReparacion = e.target.value;
    setIdReparacion(newIdReparacion);
  };

  const saveDetalle = (e) => {
    e.preventDefault();

    const formattedHoraIngreso = `${horaIngreso}:00`;

    const detalle = {
      patente,
      idReparacion,
      fechaIngreso,
      horaIngreso: formattedHoraIngreso,
      montoReparacion: 0, // Inicialmente poner montoReparacion en 0
    };

    if (id) {
      // Actualizar datos del detalle
      detallesService
        .update({ ...detalle, id })
        .then((response) => {
          console.log("Detalle ha sido actualizado.", response.data);
          navigate("/detalle/list");
        })
        .catch((error) => {
          console.log("Ha ocurrido un error al intentar actualizar datos del detalle.", error);
        });
    } else {
      // Crear nuevo detalle
      detallesService
        .create(detalle)
        .then((response) => {
          const createdDetalle = response.data;
          console.log("Detalle ha sido añadido.", createdDetalle);

          // Obtener el precio de la reparación y actualizar el detalle
          return getPrecioReparacion(idReparacion, patente)
            .then(precio => {
              console.log("Precio de reparación obtenido:", precio);
              const updatedDetalle = {
                ...createdDetalle,
                montoReparacion: precio
              };
              return detallesService.update(updatedDetalle);
            })
            .then(() => {
              console.log("Monto de reparación actualizado.");
              navigate("/detalle/list");
            });
        })
        .catch((error) => {
          console.log("Ha ocurrido un error al intentar crear nuevo detalle.", error);
        });
    }
  };

  return (
    <Box
      display="flex"
      flexDirection="column"
      alignItems="center"
      justifyContent="center"
      component="form"
      onSubmit={saveDetalle}
    >
      <h3> {titleDetalleForm} </h3>
      <hr />
      <FormControl fullWidth>
        <TextField
          id="patente"
          label="Patente"
          value={patente}
          variant="standard"
          onChange={handlePatenteChange}
          error={errorPatente}
          helperText={errorPatente ? "Formato de patente inválido" : "Ej. ABCD12"}
          inputProps={{ maxLength: 6 }}
        />
      </FormControl>

      <FormControl fullWidth>
        <TextField
          id="idReparacion"
          label="ID de reparación"
          type="number"
          value={idReparacion}
          select
          variant="standard"
          onChange={handleIdReparacionChange}
          style={{ width: "25%" }}
        >
          {[...Array(11).keys()].map((i) => (
            <MenuItem key={i} value={i + 1}>{i + 1}</MenuItem>
          ))}
        </TextField>
      </FormControl>

      <FormControl fullWidth>
        <TextField
          id="fechaIngreso"
          label="Fecha Ingreso al taller"
          type="date"
          value={fechaIngreso}
          variant="standard"
          onChange={(e) => setFechaIngreso(e.target.value)}
          InputLabelProps={{
            shrink: true,
          }}
        />
      </FormControl>

      <FormControl fullWidth>
        <TextField
          id="horaIngreso"
          label="Hora de Ingreso al taller"
          type="time"
          value={horaIngreso}
          variant="standard"
          onChange={(e) => setHoraIngreso(e.target.value)}
          InputLabelProps={{
            shrink: true,
          }}
        />
      </FormControl>

      <FormControl fullWidth>
        <TextField
          id="montoReparacion"
          label="Monto Reparación"
          type="number"
          value={montoReparacion}
          variant="standard"
          InputProps={{
            readOnly: true,
          }}
        />
      </FormControl>

      <FormControl>
        <br />
        <Button
          variant="contained"
          color="info"
          type="submit"
          style={{ marginLeft: "0.5rem" }}
          startIcon={<SaveIcon />}
        >
          Grabar
        </Button>
      </FormControl>
      <hr />
      <Link to="/detalle/list">Back to List</Link>
    </Box>
  );
};

export default AddEditDetalle;
