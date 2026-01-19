import { Link, useLocation } from "react-router-dom";
import "./MenuNavegacion.css";

export default function MenuNavegacion({
  items = [],
  classNameNav = "",
  classNameList = "",
  classNameItem = "",
  classNameLink = "",
}) {
  const location = useLocation();

  {
    /*detecto en que pagina estoy para desactivar menus en esas rutas, 
  ya que no quiero que el usuario navegue a ninguna pagina sin hacer login */
  }

  const isLogin = location.pathname === "/login";
  const isRegistro = location.pathname === "/registro";
  const isCambiar = location.pathname === "/CambiarContrasena";

  {
    /* Si estoy en alguna de las paginas arriba descritas, desactivo Menus */
  }
  const disabled = isLogin || isRegistro || isCambiar;

  return (
    <nav
      className={`${classNameNav} menu-nav ${disabled ? "nav-disabled" : ""}`}
    >
      <ul className={`${classNameList} menu-list`}>
        {items.map((item) => (
          <li key={item.to} className={`${classNameItem} menu-item`}>
            {/* Cada item del menu es un link hacia la ruta correspondiente */}

            <Link to={item.to} className={`${classNameLink} menu-link`}>
              {item.label}
            </Link>
          </li>
        ))}
      </ul>
    </nav>
  );
}
