import { useLocation } from "react-router-dom";
import MenuNavegacion from "./MenuNavegacion";
import "./NavegadorPaginas.css";

export default function NavegadorPaginas() {
  const location = useLocation();

  {
    /* Detecto si estoy en páginas donde no quiero mostrar el navegador.
  Esto lo hago para que en login, registro y recuperar contraseña
  el usuario no pueda navegar a otras secciones. */
  }
  const isLogin = location.pathname === "/login";
  const isRegistro = location.pathname === "/registro";
  const isCambiar = location.pathname === "/CambiarContrasena";

  // Si estoy en alguna de esas rutas, desactivo el navegador.
  const disabled = isLogin || isRegistro || isCambiar;

  // Lista de páginas principales del sitio.
  const paginas = [
    { to: "/servicios", label: "Servicios" },
    { to: "/salones", label: "Salones" },
    { to: "/colaboradores", label: "Estilistas" },
  ];

  return (
    <div className={`layout-navegador ${disabled ? "nav-disabled" : ""}`}>
      {/* Reutilizo el componente MenuNavegacion para mostrar las páginas */}
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
