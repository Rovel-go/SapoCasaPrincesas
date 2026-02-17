import "./BrandContent.css";
import princesita from "../assets/imagenes/PrincesitaReal1.png";

function BrandContent() {
  return (
    // Contenedor principal del logo (imagen + texto del branding)
    <div className="brand-content">
      {/* Imagen principal del logo */}
      <div className="princesita">
        <img src={princesita} alt="imagen princesita" />
      </div>

      {/* Contenedor del texto del logo */}
      <div className="brand-assets">
        {/* Letras del nombre "Sapo" con colores institucionales */}
        <div className="sapo">
          <span className="green">S</span>
          <span className="red">a</span>
          <span className="yellow">p</span>
          <span className="orange">o</span>
        </div>

        {/* Slogan debajo del nombre */}
        <div className="slogan">
          <span className="casa-princesas">Casa de princesas!</span>
        </div>
      </div>
    </div>
  );
}

export default BrandContent;
