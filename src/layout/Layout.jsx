import { Outlet } from "react-router-dom";
import "./Layout.css";
import Header from "./Header";
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
      </main>

      <footer className="layout-footer">
        <p>Footer Temporal</p>
      </footer>
    </div>
  );
}

export default Layout;
