import httpClient from "../https-common.js";

const getAll = () => {
    return httpClient.get('/vehiculos/');
}

const create = data => {
    return httpClient.post("/vehiculos/", data);
}

const get = id => {
    return httpClient.get(`/vehiculos/id/${id}`);
}

const getPatente = patente => {
    return httpClient.get(`/vehiculos/patente/${patente}`);
}

const getTipoMotor = tipoMotor => {
    return httpClient.get(`/vehiculos/tipoMotor/${tipoMotor}`);
}

const getByTipoMotor = patenteMotor => {
    return httpClient.get(`/vehiculos/patenteTipoMotor/${patenteMotor}`);
}

const getTipoVehiculo = tipoVehiculo => {
    return httpClient.get(`/vehiculos/tipoVehiculo/${tipoVehiculo}`);
}

const getMarca = marca => {
    return httpClient.get(`/vehiculos/marca/${marca}`);
}

const update = data => {
    return httpClient.put('/vehiculos/', data);
}

const remove = id => {
    return httpClient.delete(`/vehiculos/${id}`);
}
export default { getAll, create, get, getPatente, getByTipoMotor, getTipoMotor, getTipoVehiculo, getMarca, update, remove };