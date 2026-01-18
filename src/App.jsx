import { Routes, Route } from "react-router-dom";
import Layout from "./layout/Layout";
import Home from "./paginas/home/Home.jsx";
import Login from "./paginas/login/Login.jsx";
import Registro from "./paginas/registro/Registro.jsx";
import CambiarContrasena from "./paginas/cambiarContrasena/CambiarContrasena.jsx";

function App() {
  return (
    <Routes>
      <Route element={<Layout />}>
        <Route path="/home" element={<Home />} />
        <Route path="/login" element={<Login />} />
        <Route path="/registro" element={<Registro />} />
        <Route path="/CambiarContrasena" element={<CambiarContrasena />} />
      </Route>
    </Routes>
  );
}

export default App;
