import MenuNavegacion from "../componentes/MenuNavegacion";
import "./Footer.css";

export default function Footer() {
  const enlacesFooter = [
    { to: "/quienes-somos", label: "Quiénes Somos" },
    { to: "/mision", label: "Misión" },
    { to: "/vision", label: "Visión" },
    { to: "/contactanos", label: "Contáctanos" },
  ];

  return (
    <footer className="layout-footer">
      <MenuNavegacion
        items={enlacesFooter}
        classNameNav="nosotros"
        classNameList="lista-nosotros"
        classNameItem="item-nosotros"
        classNameLink="link-nosotros"
      />
    </footer>
  );
}
