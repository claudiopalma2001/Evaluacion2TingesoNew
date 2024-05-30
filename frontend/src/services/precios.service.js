import httpClient from "../https-common.js";

const getAll = () => {
    return httpClient.get('/precios/');
}

const create = data => {
    return httpClient.post("/precios/", data);
}

const get = id => {
    return httpClient.get(`/precios/id/${id}`);
}

const getTipoMotor = tipoMotor => {
    return httpClient.get(`/precios/tipoMotor/${tipoMotor}`);
}

const getPrecio = (idReparacion, tipoDeMotor) => {
    return httpClient.get(`/precios/idReparacionTipoMotor`, {
        params: {
            idReparacion,
            tipoDeMotor
        }
    });
}


const update = data => {
    return httpClient.put('/precios/', data);
}

const remove = id => {
    return httpClient.delete(`/precios/${id}`);
}
export default { getAll, create, get, getTipoMotor, getPrecio, update, remove };