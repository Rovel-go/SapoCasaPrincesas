// Importo useState y useEffect porque necesito manejar estado
// y cargar los colaboradores desde el backend cuando el componente se monta.
import { useEffect, useState } from "react";

// Importo mi CSS adaptado desde el CSS de Salones.
import "./Colaboradores.css";

// Exporto el componente principal de Colaboradores.
export default function Colaboradores() {
  console.log("CARGANDO COLABORADORES.jsx");

  const [colaboradores, setColaboradores] = useState([]);
  const [colaboradorActivo, setColaboradorActivo] = useState(null);

  useEffect(() => {
    cargarColaboradores();
  }, []);

  const cargarColaboradores = async () => {
    try {
      const respuesta = await fetch("http://localhost:8081/colaboradores");
      const data = await respuesta.json();
      setColaboradores(data);
    } catch (error) {
      console.error("Error cargando colaboradores:", error);
    }
  };

  const toggleColaborador = (id) => {
    setColaboradorActivo(colaboradorActivo === id ? null : id);
  };

  return (
    <div className="salones-page">
      <div className="salon-card-container">
        {colaboradores.map((c) => (
          <div
            key={c.id}
            className="salon-card"
            onClick={() => toggleColaborador(c.id)}
          >
            {/* Imagen */}
            <div className="salon-img">
              <img
                src={`http://localhost:8081/imagenes/colaboradores/${c.foto}`}
                alt={c.nombre}
              />
            </div>

            {/* Nombre */}
            <div className="salon-leyenda">
              <h1>{c.nombre}</h1>
            </div>

            {/* Detalle expandible */}
            <div
              className={`salon-detalle ${
                colaboradorActivo === c.id ? "open" : ""
              }`}
            >
              <ul>
                <li>
                  <strong>Rol:</strong> {c.rol}
                </li>
                <li>
                  <strong>Especialidad:</strong> {c.especialidad}
                </li>
                <li>
                  <strong>Experiencia:</strong> {c.experiencia}
                </li>
              </ul>
            </div>
          </div>
        ))}
      </div>
    </div>
  );
}
