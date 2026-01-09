import "./Home.css";
import { Link } from "react-router-dom";
import modelo from "/imagenes/modelo.png";

function Home() {
  return (
    <div className="home">
      <nav className="menu-clasificador">
        <ul>
          <li>
            <Link to="/servicios">Servicios</Link>
          </li>
        </ul>
        <ul>
          <li>
            <Link to="/salones">Salones</Link>
          </li>
        </ul>
        <ul>
          <li>
            <Link to="/colaboradores">Colaboradores</Link>
          </li>
        </ul>
      </nav>

      <div className="modelo">
        <img src={modelo} alt="imagen modelo" />
      </div>
    </div>
  );
}

export default Home;
