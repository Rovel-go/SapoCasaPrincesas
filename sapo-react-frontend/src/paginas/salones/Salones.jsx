import { useEffect, useState } from "react";
import "./Salones.css";

export default function Salones() {
  const [salones, setSalones] = useState([]);
  const [salonActivo, setSalonActivo] = useState(null);

  useEffect(() => {
    cargarSalones();
  }, []);

  const cargarSalones = async () => {
    try {
      const respuesta = await fetch("http://localhost:8081/salones");
      const data = await respuesta.json();
      setSalones(data);
    } catch (error) {
      console.error("Error cargando salones:", error);
    }
  };

  const toggleSalon = (id) => {
    setSalonActivo(salonActivo === id ? null : id);
  };

  return (
    <div className="salones-page">
      <div className="salon-card-container">
        {salones.map((s) => (
          <div
            key={s.id}
            className="salon-card"
            onClick={() => toggleSalon(s.id)}
          >
            <div className="salon-img">
              {console.log("FOTO:", s.foto)}
              <img
                src={`http://localhost:8081/imagenes/salones/${s.foto}`}
                alt={s.nombre}
              />
            </div>

            <div className="salon-leyenda">
              <h1>{s.nombre}</h1>
            </div>

            <div
              className={`salon-detalle ${salonActivo === s.id ? "open" : ""}`}
            >
              <p>{s.direccion}</p>
            </div>
          </div>
        ))}
      </div>
    </div>
  );
}
