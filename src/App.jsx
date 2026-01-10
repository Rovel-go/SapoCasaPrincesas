// Aquí se organiza la navegación principal usando React Router.

import { Routes, Route } from "react-router-dom";
// Layout general que envuelve todas las páginas con Header y Footer
import Layout from "./layout/Layout";
// Páginas
import Login from "./componentes/Login";
import AdminContrasena from "./componentes/AdminContrasena"; // ← CORRECTO
import Registro from "./componentes/Registro";
import Home from "./componentes/Home";
import Servicios from "./componentes/Servicios";
import Salones from "./componentes/Salones";
import Colaboradores from "./componentes/Colaboradores";
function App() {
  return (
    <Routes>
      {/* Todas las rutas comparten el Layout (Header + Footer + Outlet) */}
      <Route path="/" element={<Layout />}>
        {/* Página por defecto: Login */}
        <Route index element={<Login />} />
        {/* Ruta explícita para Login */}
        <Route path="login" element={<Login />} />
        {/* Flujo de recuperación de contraseña */}
        <Route
          path="recuperar"
          element={<AdminContrasena modo="recuperar" />}
        />
        {/* Registro de nuevos usuarios */}
        <Route path="registro" element={<Registro />} />
        {/* Páginas internas después del login */}
        <Route path="home" element={<Home />} />
        <Route path="servicios" element={<Servicios />} />
        <Route path="salones" element={<Salones />} />
        <Route path="colaboradores" element={<Colaboradores />} />
        {/* Página para rutas no existentes */}
        <Route path="*" element={<h2>Página no encontrada</h2>} />
      </Route>
    </Routes>
  );
}

export default App;
