import Header from "./Header";
import Footer from "./Footer";
import { Outlet, useLocation } from "react-router-dom";
import "./Layout.css";

function Layout() {
  const location = useLocation();

  const isAuthPage =
    location.pathname === "/" ||
    location.pathname === "/login" ||
    location.pathname === "/registro";

  return (
    <div className="grid-contenedor">
      <Header />

      <main className="main-contenido">
        <Outlet />
      </main>

      <Footer className={isAuthPage ? "footer-disabled" : ""} />
    </div>
  );
}

export default Layout;
