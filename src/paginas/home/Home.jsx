import { useEffect } from "react";
import "./Home.css";
import modelo from "../../assets/imagenes/modelo.png";

export default function Home() {
  // Fix de altura real del viewport en móviles.
  // Esto evita el clásico problema donde 100vh no coincide con la altura visible
  // por culpa de la barra del navegador (especialmente entre 440px–911px).
  useEffect(() => {
    const fixHeight = () => {
      document.documentElement.style.setProperty(
        "--vh",
        `${window.innerHeight * 0.01}px`
      );
    };

    fixHeight(); // lo aplico al cargar la página
    window.addEventListener("resize", fixHeight); // lo actualizo si cambia el tamaño

    return () => window.removeEventListener("resize", fixHeight);
  }, []);

  return (
    <main className="home-container">
      {/* Imagen principal de la pantalla Home */}
      <section className="card-modelo">
        <img src={modelo} alt="foto modelo" className="home-imagen" />
      </section>
    </main>
  );
}
