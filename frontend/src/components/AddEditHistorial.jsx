import { useState, useEffect } from "react";
import { Link, useParams, useNavigate } from "react-router-dom";
import Box from "@mui/material/Box";
import TextField from "@mui/material/TextField";
import Button from "@mui/material/Button";
import FormControl from "@mui/material/FormControl";
import SaveIcon from "@mui/icons-material/Save";
import historialesService from "../services/historiales.service";

const AddEditHistorial = () => {
    const [patente, setPatente] = useState("");
    const [fechaIngreso, setFechaIngreso] = useState("");
    const [horaIngreso, setHoraIngreso] = useState("");
    const [fechaSalida, setFechaSalida] = useState("");
    const [horaSalida, setHoraSalida] = useState("");
    const [fechaRecogida, setFechaDeRecogidaVehiculo] = useState("");
    const [horaRecogida, setHoraDeRecogidaVehiculo] = useState("");
    const [errorPatente, setErrorPatente] = useState(false);
    const { id } = useParams();
    const [titleHistorialForm, setTitleHistorialForm] = useState("");
    const navigate = useNavigate();

    const saveHistorial = (e) => {
        e.preventDefault();

        const formattedHoraIngreso = `${horaIngreso}:00`;
        const formattedHoraSalida = `${horaSalida}:00`;
        const formattedHoraRecogida = `${horaRecogida}:00`;

        const historial = {
            patente, 
            fechaIngreso, 
            horaIngreso: formattedHoraIngreso, 
            fechaSalida,
            horaSalida: formattedHoraSalida,
            fechaRecogida,
            horaRecogida: formattedHoraRecogida,
            id 
        };

        if (id) {
            // Actualizar Datos Historial
            historialesService.update(historial)
                .then((response) => {
                    console.log("El historial ha sido actualizado.", response.data);
                    navigate("/historial/list");
                })
                .catch((error) => {
                    console.log("Ha ocurrido un error al intentar actualizar datos del vehiculo.", error);
                });
        } else {
            // Crear nuevo historial
            historialesService.create(historial)
                .then((response) => {
                    console.log("Historial ha sido añadido.", response.data);
                    navigate("/historial/list");
                })
                .catch((error) => {
                    console.log("Ha ocurrido un error al intentar crear nuevo historial.", error);
                });
        }
    };

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

    const handleCalculate = async (patente) => {
        try {
            const response = await historialesService.calculate(patente);
            console.log("Datos calculados", response.data);
            // Actualizar los valores en el historial (opcional)
        } catch (error) {
            console.log("Error al calcular los datos", error);
        }
    };

    useEffect(() => {
        if (id) {
            setTitleHistorialForm("Editar Historial");
            historialesService.get(id)
                .then((historial) => {
                    setPatente(historial.data.patente);
                    setFechaIngreso(historial.data.fechaIngreso);
                    setHoraIngreso(historial.data.horaIngreso);
                    setFechaSalida(historial.data.fechaSalida);
                    setHoraSalida(historial.data.horaSalida);
                    setFechaDeRecogidaVehiculo(historial.data.fechaRecogida);
                    setHoraDeRecogidaVehiculo(historial.data.horaRecogida);
                })
                .catch((error) => {
                    console.log("Se ha producido un error.", error);
                });
        } else {
            setTitleHistorialForm("Nuevo Historial");
        }
    }, [id]);

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
                        onChange={handlePatenteChange}
                        error={errorPatente}
                        helperText={errorPatente ? "Formato de patente inválido" : "Ej. ABCD12"}
                        inputProps={{ maxLength: 6 }}
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
                        value={fechaRecogida}
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
                        value={horaRecogida}
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
            <br />
            {id && (
                <Button
                    variant="contained"
                    color="primary"
                    onClick={() => handleCalculate(patente)}
                >
                    Calcular Datos
                </Button>
            )}
            <hr />
            <Link to="/historial/list">Back to List</Link>
        </Box>
    );
};

export default AddEditHistorial;
