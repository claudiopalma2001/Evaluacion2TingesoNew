import { useState, useEffect } from "react";
import { Link, useParams, useNavigate } from "react-router-dom";
import Box from "@mui/material/Box";
import TextField from "@mui/material/TextField";
import Button from "@mui/material/Button";
import FormControl from "@mui/material/FormControl";
import MenuItem from "@mui/material/MenuItem";
import SaveIcon from "@mui/icons-material/Save";
import historialesService from "../services/historiales.service";

const AddEditHistorial = () => {
    const [patente, setPatente] = useState("");
    const [fechaIngreso, setFechaIngreso] = useState("");
    const [horaIngreso, setHoraIngreso] = useState("");
    const [montoTotalReparacion, setMontoTotalReparacion] = useState("");
    const [recargos, setRecargos] = useState("");
    const [descuentos, setDescuentos] = useState("");
    const [iva, setIva] = useState("");
    const [costoTotal, setCostoTotal] = useState("");
    const [fechaSalida, setFechaSalida] = useState("");
    const [horaSalida, setHoraSalida] = useState("");
    const [fechaDeRecogidaVehiculo, setFechaDeRecogidaVehiculo] = useState("");
    const [horaDeRecogidaVehiculo, setHoraDeRecogidaVehiculo] = useState("");
    const { id } = useParams();
    const [titleHistorialForm, setTitleHistorialForm] = useState("");
    const navigate = useNavigate();

  const saveHistorial = (e) => {
    e.preventDefault();

    const formattedHoraIngreso = `${horaIngreso}:00`;
    const formattedHoraSalida = `${horaSalida}:00`;
    const formattedHoraRecogida = `${horaDeRecogidaVehiculo}:00`;

    const historial = {
        patente, 
        fechaIngreso, 
        horaIngreso: formattedHoraIngreso, 
        montoTotalReparacion, 
        recargos,
        descuentos,
        iva,
        costoTotal,
        fechaSalida: fechaSalida,
        horaSalida: formattedHoraSalida,
        fechaDeRecogidaVehiculo: fechaDeRecogidaVehiculo,
        horaDeRecogidaVehiculo: formattedHoraRecogida,
        id 
    };
    if (id) {
      //Actualizar Datos Historial
      historialesService
        .update(historial)
        .then((response) => {
          console.log("El historial ha sido actualizado.", response.data);
          navigate("/historial/list");
        })
        .catch((error) => {
          console.log(
            "Ha ocurrido un error al intentar actualizar datos del vehiculo.",
            error
          );
        });
    } else {
      //Crear nuevo historial
      historialesService
        .create(historial)
        .then((response) => {
          console.log("Historial ha sido añadido.", response.data);
          navigate("/historial/list");
        })
        .catch((error) => {
          console.log(
            "Ha ocurrido un error al intentar crear nuevo historial.",
            error
          );
        });
    }
  };

  useEffect(() => {
    if (id) {
      setTitleHistorialForm("Editar Historial");
      historialesService
        .get(id)
        .then((historial) => {
            setPatente(historial.data.patente);
            setFechaIngreso(historial.data.fechaIngreso);
            setHoraIngreso(historial.data.formattedHoraIngreso);
            setMontoTotalReparacion(historial.data.montoTotalReparacion);
            setRecargos(historial.data.recargos);
            setDescuentos(historial.data.descuentos);
            setIva(historial.data.iva);
            setCostoTotal(historial.data.costoTotal);
            setFechaSalida(historial.data.fechaSalida);
            setHoraSalida(historial.data.formattedHoraIngreso);
            setFechaDeRecogidaVehiculo(historial.data.fechaDeRecogidaVehiculo);
            setHoraDeRecogidaVehiculo(historial.data.formattedHoraRecogida);
        })
        .catch((error) => {
          console.log("Se ha producido un error.", error);
        });
    } else {
    setTitleHistorialForm("Nuevo Historial");
    }
  }, []);

  return (
    <Box
      display="flex"
      flexDirection="column"
      alignItems="center"
      justifyContent="center"
      component="form"
    >
      <h3> {titleHistorialForm} </h3>
      <hr />
      <form>
        <FormControl fullWidth>
          <TextField
            id="patente"
            label="Patente"
            value={patente}
            variant="standard"
            onChange={(e) => setPatente(e.target.value)}
            helperText="Ej. ABCD12"
          />
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
            id="montoTotalReparacion"
            label="Monto de la reparacion"
            type="number"
            value={montoTotalReparacion}
            variant="standard"
            onChange={(e) => setMontoTotalReparacion(e.target.value)}
          />
        </FormControl>

        <FormControl fullWidth>
          <TextField
            id="recargos"
            label="Recargos de la reparacion"
            type="number"
            value={recargos}
            variant="standard"
            onChange={(e) => setRecargos(e.target.value)}
          />
        </FormControl>

        <FormControl fullWidth>
          <TextField
            id="descuentos"
            label="Descuentos de la reparacion"
            type="number"
            value={descuentos}
            variant="standard"
            onChange={(e) => setDescuentos(e.target.value)}
          />
        </FormControl>

        <FormControl fullWidth>
          <TextField
            id="iva"
            label="IVA de la reparacion"
            type="number"
            value={iva}
            variant="standard"
            onChange={(e) => setIva(e.target.value)}
          />
        </FormControl>

        <FormControl fullWidth>
          <TextField
            id="costoTotal"
            label="IVA de la reparacion"
            type="number"
            value={costoTotal}
            variant="standard"
            onChange={(e) => setCostoTotal(e.target.value)}
          />
        </FormControl>

        <FormControl fullWidth>
          <TextField
            id="fechaSalida"
            label="Fecha de salida del taller"
            type="date"
            value={fechaSalida}
            variant="standard"
            onChange={(e) => setFechaSalida(e.target.value)}
            InputLabelProps={{
              shrink: true,
            }}
          />
        </FormControl>

        <FormControl fullWidth>
      <TextField
        id="horaSalida"
        label="Hora de salida del taller"
        type="time"
        value={horaSalida}
        variant="standard"
        onChange={(e) => setHoraSalida(e.target.value)}
        InputLabelProps={{
          shrink: true,
        }}
          />
        </FormControl>

        <FormControl fullWidth>
          <TextField
            id="fechaDeRecogidaVehiculo"
            label="Fecha de recogida del vehiculo"
            type="date"
            value={fechaDeRecogidaVehiculo}
            variant="standard"
            onChange={(e) => setFechaDeRecogidaVehiculo(e.target.value)}
            InputLabelProps={{
              shrink: true,
            }}
          />
        </FormControl>

        <FormControl fullWidth>
      <TextField
        id="horaDeRecogidaVehiculo"
        label="Hora de recogida del vehiculo"
        type="time"
        value={horaDeRecogidaVehiculo}
        variant="standard"
        onChange={(e) => setHoraDeRecogidaVehiculo(e.target.value)}
        InputLabelProps={{
          shrink: true,
        }}
          />
        </FormControl>

        <FormControl>
          <br />
          <Button
            variant="contained"
            color="info"
            onClick={(e) => saveHistorial(e)}
            style={{ marginLeft: "0.5rem" }}
            startIcon={<SaveIcon />}
          >
            Grabar
          </Button>
        </FormControl>
      </form>
      <hr />
      <Link to="/historial/list">Back to List</Link>
    </Box>
  );
};

export default AddEditHistorial;
