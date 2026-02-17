import { Outlet } from "react-router-dom";
import "./Layout.css";
import Header from "./Header";
import Footer from "./Footer";
import NavegadorPaginas from "../componentes/NavegadorPaginas";
import fondo from "../assets/imagenes/fondo.png";

function Layout() {
  return (
    // Contenedor general del layout. Aquí aplico el fondo institucional.
    <div
      className="layout-container"
      style={{ backgroundImage: `url(${fondo})` }}
    >
      {/* Header con menú superior y branding */}
      <Header />

      {/* Contenido principal: navegador de páginas + Outlet */}
      <main className="layout-main">
        {/* Navegador interno (Servicios, Salones, Colaboradores) */}
        <NavegadorPaginas />

        {/* Aquí React Router carga la página correspondiente */}
        <div className="outlet-wrapper">
          <Outlet />
        </div>
      </main>

      {/* Footer institucional */}
      <Footer />
    </div>
  );
}

export default Layout;
