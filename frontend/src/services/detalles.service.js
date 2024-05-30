import httpClient from "../https-common.js";

const getAll = () => {
    return httpClient.get('/detalles/');
}

const create = data => {
    return httpClient.post("/detalles/", data);
}

const get = id => {
    return httpClient.get(`/detalles/id/${id}`);
}

const getIdReparacion = idReparacion => {
    return httpClient.get(`/detalles/idReparacion/${idReparacion}`);
}

const getPatente = patente => {
    return httpClient.get(`/detalles/patente/${patente}`);
}

const getPatentes = patentes => {
    return httpClient.get(`/detalles/patentes/${patentes}`);
}

const update = data => {
    return httpClient.put('/detalles/', data);
}

const remove = id => {
    return httpClient.delete(`/detalles/${id}`);
}
export default { getAll, create, get, getIdReparacion, getPatente, getPatentes, update, remove };