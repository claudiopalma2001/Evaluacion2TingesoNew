import './App.css'
import {BrowserRouter as Router, Route, Routes} from 'react-router-dom'
import Navbar from "../src/components/NavBar"
import Home from './components/Home';
import NotFound from './components/NotFound';
import VehiculoList from './components/VehiculosList';
import AddEditVehiculo from './components/AddEditVehiculo';
import HistorialList from './components/HistorialList';
import AddEditHistorial from './components/AddEditHistorial';
import PrecioList from './components/PrecioList';
import DetalleList from './components/DetalleList';
import AddEditDetalle from './components/AddEditDetalle';

function App() {
  return (
    <Router>
        <div className="container">
        <Navbar></Navbar>
          <Routes>
            <Route path="/home" element={<Home/>} />
            <Route path="/vehiculo/list" element={<VehiculoList/>} />
            <Route path="/vehiculo/add" element={<AddEditVehiculo/>} />
            <Route path="/vehiculo/edit/:id" element={<AddEditVehiculo/>} />
            <Route path="/historial/list" element={<HistorialList/>} />
            <Route path="/historial/add" element={<AddEditHistorial/>} />
            <Route path="/historial/edit/:id" element={<AddEditHistorial/>} />
            <Route path="/detalle/list" element={<DetalleList/>} />
            <Route path="/detalle/add" element={<AddEditDetalle/>} />
            <Route path="/detalle/edit/:id" element={<AddEditDetalle/>} />
            <Route path="/precio/list" element={<PrecioList/>} />
            <Route path="*" element={<NotFound/>} />
          </Routes>
        </div>
    </Router>
);
}

export default App
