import { useState, useEffect } from "react";
import { Link, useParams, useNavigate } from "react-router-dom";
import Box from "@mui/material/Box";
import TextField from "@mui/material/TextField";
import Button from "@mui/material/Button";
import FormControl from "@mui/material/FormControl";
import MenuItem from "@mui/material/MenuItem";
import SaveIcon from "@mui/icons-material/Save";
import vehiculosService from "../services/vehiculos.service";

const AddEditVehiculo = () => {
  const [patente, setPatente] = useState("");
  const [marca, setMarca] = useState("");
  const [modelo, setModelo] = useState("");
  const [tipoVehiculo, setTipoVehiculo] = useState("");
  const [anio, setAnio] = useState("");
  const [tipoMotor, setTipoMotor] = useState("");
  const [kilometraje, setKilometraje] = useState("");
  const [errorPatente, setErrorPatente] = useState(false);
  const { id } = useParams();
  const [titleVehiculoForm, setTitleVehiculoForm] = useState("");
  const navigate = useNavigate();

  const saveVehiculo = (e) => {
    e.preventDefault();

    const vehiculo = { patente, marca, modelo, tipoVehiculo, anio, tipoMotor, kilometraje, id};
    if (id) {
      //Actualizar Datos Vehiculo
      vehiculosService
        .update(vehiculo)
        .then((response) => {
          console.log("Vehiculo ha sido actualizado.", response.data);
          navigate("/vehiculo/list");
        })
        .catch((error) => {
          console.log(
            "Ha ocurrido un error al intentar actualizar datos del vehiculo.",
            error
          );
        });
    } else {
      //Crear nuevo vehiculo
      vehiculosService
        .create(vehiculo)
        .then((response) => {
          console.log("Vehiculo ha sido añadido.", response.data);
          navigate("/vehiculo/list");
        })
        .catch((error) => {
          console.log(
            "Ha ocurrido un error al intentar crear nuevo vehiculo.",
            error
          );
        });
    }
  };

  useEffect(() => {
    if (id) {
      setTitleVehiculoForm("Editar Vehiculo");
      vehiculosService
        .get(id)
        .then((vehiculo) => {
          setPatente(vehiculo.data.patente);
          setMarca(vehiculo.data.marca);
          setModelo(vehiculo.data.modelo);
          setTipoVehiculo(vehiculo.data.tipoVehiculo);
          setAnio(vehiculo.data.anio);
          setTipoMotor(vehiculo.data.tipoMotor);
          setKilometraje(vehiculo.data.kilometraje);
        })
        .catch((error) => {
          console.log("Se ha producido un error.", error);
        });
    } else {
      setTitleVehiculoForm("Nuevo Vehiculo");
    }
  }, []);

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

  return (
    <Box
      display="flex"
      flexDirection="column"
      alignItems="center"
      justifyContent="center"
      component="form"
    >
      <h3> {titleVehiculoForm} </h3>
      <hr />
      <form>
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
            id="marca"
            label="Marca"
            value={marca}
            variant="standard"
            onChange={(e) => setMarca(e.target.value)}
          />
        </FormControl>

        <FormControl fullWidth>
          <TextField
            id="modelo"
            label="Modelo"
            value={modelo}
            variant="standard"
            onChange={(e) => setModelo(e.target.value)}
          />
        </FormControl>

        <FormControl fullWidth>
          <TextField
            id="tipoVehiculo"
            label="Tipo de vehiculo"
            value={tipoVehiculo}
            select
            variant="standard"
            defaultValue="A"
            onChange={(e) => setTipoVehiculo(e.target.value)}
            style={{ width: "25%" }}
          >
            <MenuItem value={"Sedán"}>Sedán</MenuItem>
            <MenuItem value={"Hatchback"}>Hatchback</MenuItem>
            <MenuItem value={"SUV"}>SUV</MenuItem>
            <MenuItem value={"Pickup"}>Pickup</MenuItem>
            <MenuItem value={"Furgoneta"}>Furgoneta</MenuItem>
          </TextField>
        </FormControl>

        <FormControl fullWidth>
          <TextField
            id="anio"
            label="Año"
            type="number"
            value={anio}
            variant="standard"
            onChange={(e) => setAnio(e.target.value)}
            helperText="Año de adquisición del vehiculo"
          />
        </FormControl>

        <FormControl fullWidth>
          <TextField
            id="tipoMotor"
            label="Tipo de motor"
            value={tipoMotor}
            select
            variant="standard"
            defaultValue="A"
            onChange={(e) => setTipoMotor(e.target.value)}
            style={{ width: "25%" }}
          >
            <MenuItem value={"Gasolina"}>Gasolina</MenuItem>
            <MenuItem value={"Diésel"}>Diésel</MenuItem>
            <MenuItem value={"Híbrido"}>Híbrido</MenuItem>
            <MenuItem value={"Eléctrico"}>Eléctrico</MenuItem>
          </TextField>
        </FormControl>

        <FormControl fullWidth>
          <TextField
            id="kilometraje"
            label="Kilometraje"
            type="number"
            value={kilometraje}
            variant="standard"
            onChange={(e) => setKilometraje(e.target.value)}
            helperText="Kilometraje del vehiculo"
          />
        </FormControl>

        <FormControl>
          <br />
          <Button
            variant="contained"
            color="info"
            onClick={(e) => saveVehiculo(e)}
            style={{ marginLeft: "0.5rem" }}
            startIcon={<SaveIcon />}
          >
            Grabar
          </Button>
        </FormControl>
      </form>
      <hr />
      <Link to="/vehiculo/list">Back to List</Link>
    </Box>
  );
};

export default AddEditVehiculo;
