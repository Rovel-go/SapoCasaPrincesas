import "./Header.css";
import MenuEnlaces from "./MenuEnlaces";
import BrandContent from "./BrandContent";

function Header() {
  return (
    <header className="header-principal">
      <MenuEnlaces />
      <BrandContent />
    </header>
  );
}

export default Header;
