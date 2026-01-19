import { Routes, Route, Navigate } from "react-router-dom";
import Layout from "./layout/Layout";
import Home from "./paginas/home/Home.jsx";
import Login from "./paginas/login/Login.jsx";
import Registro from "./paginas/registro/Registro.jsx";
import CambiarContrasena from "./paginas/cambiarContrasena/CambiarContrasena.jsx";
import RutaProtegida from "./RutaProtegida.jsx";

function App() {
  return (
    <Routes>
      {/* Layout envuelve todas las páginas (header, footer, fondo, etc.) */}
      <Route element={<Layout />}>
        {/* Redirección inicial: si entran a "/", los envío al login */}
        <Route path="/" element={<Navigate to="/login" replace />} />

        {/* Página pública */}
        <Route path="/login" element={<Login />} />

        {/* Página protegida: solo entra si está logueado */}
        <Route
          path="/home"
          element={
            <RutaProtegida>
              <Home />
            </RutaProtegida>
          }
        />

        {/* Registro también protegido (solo accesible desde sesión activa) */}
        <Route
          path="/registro"
          element={
            <RutaProtegida>
              <Registro />
            </RutaProtegida>
          }
        />

        {/* Recuperar contraseña SIEMPRE debe ser pública */}
        <Route path="/CambiarContrasena" element={<CambiarContrasena />} />
      </Route>
    </Routes>
  );
}

export default App;
