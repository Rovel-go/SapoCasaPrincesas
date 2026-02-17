import { useEffect } from "react";
import "./Home.css";
import modelo from "../../assets/imagenes/modelo.png";

export default function Home() {
  const mensajeBienvenida = sessionStorage.getItem("mensajeBienvenida");
  useEffect(() => {
    if (mensajeBienvenida) {
      setTimeout(() => {
        sessionStorage.removeItem("mensajeBienvenida");
      }, 4000);
    }
  }, []);

  useEffect(() => {
    if (mensajeBienvenida) {
      setTimeout(() => {
        sessionStorage.removeItem("mensajeBienvenida");
      }, 4000);
    }
  }, []);

  const usuario = JSON.parse(sessionStorage.getItem("usuario"));

  return (
    <div className="home-container">
      {/* Aquí sigue tu menú, imagen, servicios, etc. */}
      <section className="card-modelo">
        <img src={modelo} alt="foto modelo" className="home-imagen" />
      </section>
    </div>
  );

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
