import MenuNavegacion from "../componentes/MenuNavegacion";
import "./Footer.css";

export default function Footer() {
  // Defino los enlaces del footer; así mantengo el menú centralizado y fácil de modificar
  const enlacesFooter = [
    { to: "/quienes-somos", label: "Quiénes Somos" },
    { to: "/mision", label: "Misión" },
    { to: "/vision", label: "Visión" },
    { to: "/contactanos", label: "Contáctanos" },
  ];

  return (
    <footer className="layout-footer">
      {/* Reutilizo MenuNavegacion para evitar duplicar código */}
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
