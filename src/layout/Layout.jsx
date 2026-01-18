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
        <NavegadorPaginas />

        <div className="outlet-wrapper">
          <Outlet />
        </div>
      </main>

      <Footer />
    </div>
  );
}

export default Layout;
