import "./Header.css";
import { Link, useLocation } from "react-router-dom";
import PrincesitaReal1 from "/imagenes/PrincesitaReal1.png";

function Header() {
  const location = useLocation();

  const isAuthPage = ["/", "/login", "/registro"].includes(location.pathname);

  /*location.pathname === "/" ||
    location.pathname === "/login" ||
    location.pathname === "/registro";*/

  return (
    <header className="header">
      {/* Menú superior */}
      <nav className="menu-enlaces">
        {/* HOME: habilitado en todas las páginas excepto login */}
        {isAuthPage ? (
          <div id="logo_home" className="home-disabled">
            <i className="fa-solid fa-house"></i>
          </div>
        ) : (
          <Link id="logo_home" to="/home">
            <i className="fa-solid fa-house"></i>
          </Link>
        )}

        <div className="iconos-sociales">
          <a className="insta" href="#">
            <i className="fa-brands fa-instagram"></i>
          </a>
          <a className="tik-tok" href="#">
            <i className="fa-brands fa-tiktok"></i>
          </a>
          <a className="face" href="#">
            <i className="fa-brands fa-facebook"></i>
          </a>
        </div>

        {/* Salir siempre habilitado */}
        <Link id="logo_salir" to="/login">
          <i className="fa-solid fa-right-from-bracket"></i>
        </Link>
      </nav>

      {/* Branding */}
      <div className="brand-content">
        <div className="princesita">
          <img src={PrincesitaReal1} alt="princesita" />
        </div>

        <div className="brand-assets">
          <div className="sapo">
            <span className="green">S</span>
            <span className="red">A</span>
            <span className="yellow">P</span>
            <span className="orange">O</span>
          </div>

          <div className="slogan">
            <h2 className="casa-princesas">Casa de Princesas</h2>
          </div>
        </div>
      </div>
    </header>
  );
}

export default Header;
