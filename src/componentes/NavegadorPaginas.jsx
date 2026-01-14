import MenuNavegacion from "./MenuNavegacion";
import "./NavegadorPaginas.css";

export default function NavegadorPaginas() {
  const paginas = [
    { to: "/servicios", label: "Servicios" },
    { to: "/salones", label: "Salones" },
    { to: "/colaboradores", label: "Estilistas" },
  ];

  return (
    <div className="layout-navegador">
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
