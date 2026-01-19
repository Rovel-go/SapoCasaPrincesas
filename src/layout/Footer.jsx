import MenuNavegacion from "../componentes/MenuNavegacion";
import "./Footer.css";

export default function Footer() {
  // Enlaces que quiero mostrar en el footer. Los manejo como un arreglo
  // para reutilizar el componente MenuNavegacion igual que en el header.
  const enlacesFooter = [
    { to: "/quienes-somos", label: "Quiénes Somos" },
    { to: "/mision", label: "Misión" },
    { to: "/vision", label: "Visión" },
    { to: "/contactanos", label: "Contáctanos" },
  ];

  return (
    // Contenedor principal del footer
    <footer className="layout-footer">
      {/* Reutilizo MenuNavegacion para no duplicar código */}
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
