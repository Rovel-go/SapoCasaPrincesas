// Archivo principal de rutas de la aplicación.
// Aquí se define la estructura de navegación usando React Router.

import { Routes, Route } from "react-router-dom";

// El componente <Layout /> envuelve todas las páginas para compartir el header y el diseño general.
import Layout from "./layout/Layout";

import Login from "./componentes/Login";
import Recuperar from "./componentes/Recuperar";
import Registro from "./componentes/Registro";

function App() {
  return (
    <Routes>
      {/* Ruta base que usa el layout general */}
      <Route path="/" element={<Layout />}>
        {/* Página principal: Login */}
        <Route index element={<Login />} />

        {/* Recuperar contraseña */}
        <Route path="recuperar" element={<Recuperar />} />

        {/* Página de Registro */}
        <Route path="registro" element={<Registro />} />
      </Route>
    </Routes>
  );
}

export default App;
