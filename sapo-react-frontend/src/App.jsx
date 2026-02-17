import { Routes, Route, Navigate } from "react-router-dom";

import Servicios from "./paginas/servicios/Servicios.jsx";
import Salones from "./paginas/salones/Salones.jsx";
import Colaboradores from "./paginas/colaboradores/Colaboradores.jsx";

import QuienesSomos from "./paginas/quienesSomomos/QuienesSomos.jsx";
import Mision from "./paginas/mision/Mision.jsx";
import Vision from "./paginas/vision/Vision.jsx";
import Contactanos from "./paginas/contactanos/Contactanos.jsx";

import Layout from "./layout/Layout";
import Home from "./paginas/home/Home.jsx";
import Login from "./paginas/login/Login.jsx";
import Registro from "./paginas/registro/Registro.jsx";
import CambiarContrasena from "./paginas/cambiarContrasena/CambiarContrasena.jsx";
import RutaProtegida from "./RutaProtegida.jsx";

function App() {
  return (
    <Routes>
      {/* Uso el Layout para mantener la estructura general del sitio */}
      <Route element={<Layout />}>
        {/* Siempre redirijo la raíz hacia login */}
        <Route path="/" element={<Navigate to="/login" replace />} />

        {/* Rutas públicas: aquí no debe haber protección */}
        {/* Login debe ser accesible sin sesión */}
        <Route path="/login" element={<Login />} />

        {/* Registro también debe ser público para permitir crear cuenta */}
        <Route path="/registro" element={<Registro />} />

        {/* Recuperar contraseña igualmente es público */}
        <Route path="/CambiarContrasena" element={<CambiarContrasena />} />

        {/* Rutas protegidas: solo accesibles si hay sesión activa */}
        <Route
          path="/home"
          element={
            <RutaProtegida>
              <Home />
            </RutaProtegida>
          }
        />

        <Route
          path="/servicios"
          element={
            <RutaProtegida>
              <Servicios />
            </RutaProtegida>
          }
        />

        <Route
          path="/salones"
          element={
            <RutaProtegida>
              <Salones />
            </RutaProtegida>
          }
        />

        <Route
          path="/colaboradores"
          element={
            <RutaProtegida>
              <Colaboradores />
            </RutaProtegida>
          }
        />

        {/* Estas secciones informativas también deben estar protegidas */}
        <Route
          path="/quienes-somos"
          element={
            <RutaProtegida>
              <QuienesSomos />
            </RutaProtegida>
          }
        />

        <Route
          path="/mision"
          element={
            <RutaProtegida>
              <Mision />
            </RutaProtegida>
          }
        />

        <Route
          path="/vision"
          element={
            <RutaProtegida>
              <Vision />
            </RutaProtegida>
          }
        />

        <Route
          path="/contactanos"
          element={
            <RutaProtegida>
              <Contactanos />
            </RutaProtegida>
          }
        />
      </Route>
    </Routes>
  );
}

export default App;
