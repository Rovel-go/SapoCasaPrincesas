import "./MenuEnlaces.css";
import { FaHouseUser } from "react-icons/fa6";
import { FaFacebookF } from "react-icons/fa";
import { FaInstagram } from "react-icons/fa";
import { FaTiktok } from "react-icons/fa";
import { FaRightFromBracket } from "react-icons/fa6";

function MenuEnlaces() {
  return (
    // Contenedor principal del menú superior
    <nav className="menu-enlaces">
      {/* Icono Home */}
      <div className="logo-home">
        <FaHouseUser />
      </div>

      {/* Bloque central con iconos sociales */}
      <div className="iconos-sociales">
        <div className="icono-facebook">
          <FaFacebookF />
        </div>

        <div className="icono-instagram">
          <FaInstagram />
        </div>

        <div className="icono-tiktok">
          <FaTiktok />
        </div>
      </div>

      {/* Icono de salir/cerrar sesión */}
      <div className="logo-salir">
        <FaRightFromBracket />
      </div>
    </nav>
  );
}

export default MenuEnlaces;
