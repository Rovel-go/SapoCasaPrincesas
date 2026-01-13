import "./BrandContent.css";
import princesita from "../assets/imagenes/PrincesitaReal1.png";

function BrandContent() {
  return (
    <div className="brand-content">
      <div className="princesita">
        <img src={princesita} alt="imagen princesita" />
      </div>

      <div className="brand-assets">
        <div className="sapo">
          <span className="green">S</span>
          <span className="red">a</span>
          <span className="yellow">p</span>
          <span className="orange">o</span>
        </div>

        <div className="slogan">
          <span className="casa-princesas">Casa de princesas!</span>
        </div>
      </div>
    </div>
  );
}

export default BrandContent;
