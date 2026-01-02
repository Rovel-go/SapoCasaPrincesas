import { Link } from "react-router-dom";
import "./Header.css";

function Header() {
  return (
    <header className="header">
      <h1 className="logo">Casa de princesas</h1>

      <nav className="nav">
        <Link to="/">Inicio</Link>
        <Link to="/registro">Registro</Link>
        <Link to="/servicios">Servicios</Link>
        <Link to="/contacto">Contacto</Link>
      </nav>
    </header>
  );
}

export default Header;
