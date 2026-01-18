import "./MenuEnlaces.css";
import { FaHouseUser, FaRightFromBracket } from "react-icons/fa6";
import { FaFacebookF, FaInstagram, FaTiktok } from "react-icons/fa";
import { useLocation, useNavigate } from "react-router-dom";

function MenuEnlaces() {
  const location = useLocation();
  const navigate = useNavigate();

  const isLogin = location.pathname === "/login";
  const isRegistro = location.pathname === "/registro";
  const isCambiar = location.pathname === "/CambiarContrasena";

  // logo-home deshabilitado en login, registro y cambiar contraseña
  const homeDisabled = isLogin || isRegistro || isCambiar;

  // logo-salir SIEMPRE habilitado
  const salirDisabled = false;

  const handleHome = () => {
    if (!homeDisabled) navigate("/home");
  };

  const handleSalir = () => {
    navigate("/login"); // SIEMPRE redirige a login
  };

  return (
    <nav className="menu-enlaces">
      {/* LOGO HOME */}
      <div
        className={`logo-home ${homeDisabled ? "disabled-link" : ""}`}
        onClick={handleHome}
      >
        <FaHouseUser />
      </div>

      {/* ICONOS SOCIALES */}
      <div className="iconos-sociales">
        <a
          className="icono-facebook"
          href="https://www.facebook.com"
          target="_blank"
          rel="noopener noreferrer"
        >
          <FaFacebookF />
        </a>

        <a
          className="icono-instagram"
          href="https://www.instagram.com"
          target="_blank"
          rel="noopener noreferrer"
        >
          <FaInstagram />
        </a>

        <a
          className="icono-tiktok"
          href="https://www.tiktok.com"
          target="_blank"
          rel="noopener noreferrer"
        >
          <FaTiktok />
        </a>
      </div>

      {/* LOGO SALIR */}
      <div className="logo-salir" onClick={handleSalir}>
        <FaRightFromBracket />
      </div>
    </nav>
  );
}

export default MenuEnlaces;
