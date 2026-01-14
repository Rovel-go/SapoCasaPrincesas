import { Outlet } from "react-router-dom";
import "./Layout.css";
import Header from "./Header";
import Footer from "./Footer";
import NavegadorPaginas from "../componentes/NavegadorPaginas";
import fondo from "../assets/imagenes/fondo.png";

function Layout() {
  return (
    <div
      className="layout-container"
      style={{ backgroundImage: `url(${fondo})` }}
    >
      <Header />

      <main className="layout-main">
        <Outlet />

        <NavegadorPaginas />

        {/*<div style={{ height: "500px", background: "rgba(255,0,0,0.2)" }}>
          Contenido de prueba
        </div>*/}
      </main>

      <Footer />
    </div>
  );
}

export default Layout;
