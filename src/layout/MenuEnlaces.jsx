import "./MenuEnlaces.css";
import { FaHouseUser, FaRightFromBracket } from "react-icons/fa6";
import { FaFacebookF, FaInstagram, FaTiktok } from "react-icons/fa";

function MenuEnlaces() {
  return (
    <nav className="menu-enlaces">
      <div className="logo-home">
        <FaHouseUser />
      </div>
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
      <div className="logo-salir">
        <FaRightFromBracket />
      </div>
    </nav>
  );
}

export default MenuEnlaces;
