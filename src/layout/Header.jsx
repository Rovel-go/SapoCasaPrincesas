import "./Header.css";
import MenuEnlaces from "./MenuEnlaces";
import BrandContent from "./BrandContent";

function Header() {
  return (
    // Header principal de la aplicación.
    // Aquí coloco el menú superior y el branding para que siempre aparezcan juntos.
    <header className="header-principal">
      {/* Menú con Home, Salir e iconos sociales */}
      <MenuEnlaces />

      {/* Logo institucional (princesita + SAPO + slogan) */}
      <BrandContent />
    </header>
  );
}

export default Header;
