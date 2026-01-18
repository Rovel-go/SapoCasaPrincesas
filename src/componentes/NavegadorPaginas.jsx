import { useLocation } from "react-router-dom";
import MenuNavegacion from "./MenuNavegacion";
import "./NavegadorPaginas.css";

export default function NavegadorPaginas() {
  const location = useLocation();

  const isLogin = location.pathname === "/login";
  const isRegistro = location.pathname === "/registro";
  const isCambiar = location.pathname === "/CambiarContrasena";

  const disabled = isLogin || isRegistro || isCambiar;

  const paginas = [
    { to: "/servicios", label: "Servicios" },
    { to: "/salones", label: "Salones" },
    { to: "/colaboradores", label: "Estilistas" },
  ];

  return (
    <div className={`layout-navegador ${disabled ? "nav-disabled" : ""}`}>
      <MenuNavegacion
        items={paginas}
        classNameNav="nav-paginas"
        classNameList="lista-paginas"
        classNameItem=""
        classNameLink="link-pagina"
      />
    </div>
  );
}
