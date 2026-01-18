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

  const isLogin = location.pathname === "/login";
  const isRegistro = location.pathname === "/registro";
  const isCambiar = location.pathname === "/CambiarContrasena";

  const disabled = isLogin || isRegistro || isCambiar;

  return (
    <nav
      className={`${classNameNav} menu-nav ${disabled ? "nav-disabled" : ""}`}
    >
      <ul className={`${classNameList} menu-list`}>
        {items.map((item) => (
          <li key={item.to} className={`${classNameItem} menu-item`}>
            <Link to={item.to} className={`${classNameLink} menu-link`}>
              {item.label}
            </Link>
          </li>
        ))}
      </ul>
    </nav>
  );
}
