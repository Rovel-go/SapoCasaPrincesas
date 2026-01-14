import { Link } from "react-router-dom";
import "./MenuNavegacion.css";

export default function MenuNavegacion({
  items = [],
  classNameNav = "",
  classNameList = "",
  classNameItem = "",
  classNameLink = "",
}) {
  return (
    <nav className={`${classNameNav} menu-nav`}>
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
