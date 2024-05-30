import * as React from "react";
import Box from "@mui/material/Box";
import Drawer from "@mui/material/Drawer";
import List from "@mui/material/List";
import Divider from "@mui/material/Divider";
import ListItemButton from "@mui/material/ListItemButton";
import ListItemIcon from "@mui/material/ListItemIcon";
import ListItemText from "@mui/material/ListItemText";
import DirectionsCarIcon from "@mui/icons-material/DirectionsCar";
import ReceiptIcon from "@mui/icons-material/Receipt";
import CarRepairIcon from "@mui/icons-material/CarRepair";
import HomeIcon from "@mui/icons-material/Home";
import { useNavigate } from "react-router-dom";

export default function Sidemenu({ open, toggleDrawer }) {
  const navigate = useNavigate();

  const listOptions = () => (
    <Box
      role="presentation"
      onClick={toggleDrawer(false)}
    >
      <List>
        <ListItemButton onClick={() => navigate("/home")}>
          <ListItemIcon>
            <HomeIcon />
          </ListItemIcon>
          <ListItemText primary="Home" />
        </ListItemButton>

        <Divider />

        <ListItemButton onClick={() => navigate("/vehiculo/list")}>
          <ListItemIcon>
            <DirectionsCarIcon />
          </ListItemIcon>
          <ListItemText primary="Vehiculos" />
        </ListItemButton>
        
      <Divider />

      <ListItemButton onClick={() => navigate("/historial/list")}>
          <ListItemIcon>
            <CarRepairIcon />
          </ListItemIcon>
          <ListItemText primary="Historial de reparaciones" />
        </ListItemButton>
      
      <Divider />

      <ListItemButton onClick={() => navigate("/detalle/list")}>
          <ListItemIcon>
            <ReceiptIcon />
          </ListItemIcon>
          <ListItemText primary="Detalles de vehiculos" />
        </ListItemButton>
      
      <Divider />

      <ListItemButton onClick={() => navigate("/precio/list")}>
          <ListItemIcon>
            <ReceiptIcon />
          </ListItemIcon>
          <ListItemText primary="Precios de Reparaciones" />
        </ListItemButton>
      
      <Divider />
      
      </List>
    </Box>
    
    
  );

  return (
    <div>
      <Drawer anchor={"left"} open={open} onClose={toggleDrawer(false)}>
        {listOptions()}
      </Drawer>
    </div>
  );
}
