import { useEffect } from "react";
import "./Home.css";
import modelo from "../../assets/imagenes/modelo.png";

export default function Home() {
  // Fix de altura real del viewport (soluciona el rango 440px–911px)
  useEffect(() => {
    const fixHeight = () => {
      document.documentElement.style.setProperty(
        "--vh",
        `${window.innerHeight * 0.01}px`
      );
    };

    fixHeight();
    window.addEventListener("resize", fixHeight);

    return () => window.removeEventListener("resize", fixHeight);
  }, []);

  return (
    <main className="home-container">
      <section className="card-modelo">
        <img src={modelo} alt="foto modelo" className="home-imagen" />
      </section>
    </main>
  );
}
