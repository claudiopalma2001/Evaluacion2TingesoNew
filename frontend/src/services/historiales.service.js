import httpClient from "../https-common.js";

const getAll = () => {
    return httpClient.get(`/historial/`);
}

const create = data => {
    return httpClient.post("/historial/", data);
}

const get = id => {
    return httpClient.get(`/historial/id/${id}`);
}

const getIdReparacion = idReparacion => {
    return httpClient.get(`/historial/idReparacion/${idReparacion}`);
}

const getPatente = patente => {
    return httpClient.get(`/historial/patente/${patente}`);
}

const getPatentes = patentes => {
    return httpClient.get(`/historial/patentes/${patentes}`);
}

const getDetalles = () => {
    return httpClient.get(`/historial/detalles`);
}

const getDetallesVehiculo = patente => {
    return httpClient.get(`/historial/detalles/patente/${patente}`);
}

const update = data => {
    return httpClient.put(`/historial/`, data);
}

const remove = id => {
    return httpClient.delete(`/historial/${id}`);
}

const calculate = patente =>{
    return httpClient.post(`/historial/calcular/${patente}`)
}

export default { getAll, create, get, getIdReparacion, getPatente, getPatentes, getDetalles, getDetallesVehiculo, update, remove, calculate };